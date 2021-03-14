package org.example.project.api.projetos;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("projetos")
public class ProjetoResource {
    // CRUD - CREATE, READ, UPDATE, DELETE

    private static ProjetoRepository projetoRepository = new ProjetoRepository();


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid ProjetoRequest request) {
        Projeto projeto = new Projeto(request.titulo, request.data_inicio, request.data_fim, request.codigo_departamento);
        projetoRepository.save(projeto);
        return Response.status(Response.Status.CREATED)
                .entity(new ProjetoResponse(projeto.getCodigo_departamento(), projeto.getCodigo(), projeto.getTitulo(), projeto.getData_inicio(), projeto.getData_fim()))
                //Aqui podemos ter um controle mais fino do que é devolvido para o cliente
                //Poderiamos também mudar o MediaType nesse campo
                //
                .build();
    }


    @GET
    //Sempre que necessário buscar informação via API usamos o GET
    //Não utilizamos @Consumes já que o GET não consome nada
    //Ele vai produzir o MediaType, portanto utilizamos o @Produces
    @Produces(MediaType.APPLICATION_JSON)
    public Response index() {
//    List<UserResponse> userResponses = new ArrayList<>();
//    for (User user: userRepository.getAll() ) {
//      userResponses.add(new UserResponse(user));
//    }

        List<ProjetoResponse> projetoResponses = projetoRepository.getAll().stream()
                .map(ProjetoResponse :: new)
                .collect(Collectors.toList());

        return Response.status(Response.Status.OK).entity(projetoResponses).build();
    }

    //
    @GET
    @Path("{codigo}")//Sempre que for colocado entre chaves em uma definição de @Path, ele vai vir como váriavel.
    //show para pegar um só, padronização.
    @Produces(MediaType.APPLICATION_JSON)
    public Response show(@PathParam ("codigo"/*Colocar o mesmo nome definido em @Path*/)int codigo) {
        Projeto projeto = projetoRepository.getByCodigo(codigo);

        if(projeto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
            //Caso o usuário informe um valor inexistente retornar 404
        }

        return Response.status(Response.Status.OK).entity(new ProjetoResponse(projeto)).build();
    }
    @PUT
    @Path("{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("codigo") int codigo, @Valid ProjetoRequest request) {
        Projeto projeto = projetoRepository.getByCodigo(codigo);

        if(projeto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
            //Caso o usuário informe um valor inexistente retornar 404
        }

        Projeto newProjeto = new Projeto(request.titulo, request.data_inicio, request.data_fim, codigo, request.codigo_departamento);
        projetoRepository.update(newProjeto);
        return Response.status(Response.Status.OK).entity(new ProjetoResponse(newProjeto)).build();
    }


    @DELETE
    @Path("{codigo}")
    public Response delete(@PathParam("codigo")int codigo) {
        Projeto projeto = projetoRepository.getByCodigo(codigo);

        if(projeto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
            //Caso o usuário informe um valor inexistente retornar 404
        }

        projetoRepository.delete(projeto);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
