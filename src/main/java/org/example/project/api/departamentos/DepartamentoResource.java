package org.example.project.api.departamentos;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.util.List;
import java.util.stream.Collectors;

//Quando /api/departamentos for acessado irá cair nesta classe
@Path("departamentos")
public class DepartamentoResource {

    // CRUD - CREATE, READ, UPDATE, DELETE

    private static DepartamentoRepository departamentoRepository = new DepartamentoRepository();


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid DepartamentoRequest request) {
        Departamento departamento = new Departamento(request.nome, request.sigla);
        departamentoRepository.save(departamento);
        return Response.status(Response.Status.CREATED)
                .entity(new DepartamentoResponse(departamento.getCodigo(), departamento.getNome(), departamento.getSigla()))
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
//    List<DepartamentoResponse> departamentoResponses = new ArrayList<>();
//    for (Departamento departamento: departamentoRepository.getAll() ) {
//      departamentoResponses.add(new DepartamentoResponse(departamento));
//    }

        List<DepartamentoResponse> departamentoResponses = departamentoRepository.getAll().stream()
                .map(DepartamentoResponse :: new)
                .collect(Collectors.toList());

        return Response.status(Response.Status.OK).entity(departamentoResponses).build();
    }

    //
    @GET
    @Path("{id}")//Sempre que for colocado entre chaves em uma definição de @Path, ele vai vir como váriavel.
    //show para pegar um só, padronização.
    @Produces(MediaType.APPLICATION_JSON)
    public Response show(@PathParam ("id"/*Colocar o mesmo nome definido em @Path*/)int codigo) {
        Departamento departamento = departamentoRepository.getByCodigo(codigo);
        if(departamento == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
            //Caso o usuário informe um valor inexistente retornar 404
        }

        return Response.status(Response.Status.OK).entity(new DepartamentoResponse(departamento)).build();
    }
    @PUT
    @Path("{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("codigo") int codigo, @Valid DepartamentoRequest request) {
        Departamento departamento = departamentoRepository.getByCodigo(codigo);

        if(departamento == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
            //Caso o usuário informe um valor inexistente retornar 404
        }

        Departamento newDepartamento = new Departamento(codigo, request.nome, request.sigla);
        departamentoRepository.update(newDepartamento);
        return Response.status(Status.OK).entity(new DepartamentoResponse(newDepartamento)).build();
    }


    @DELETE
    @Path("{codigo}")
    public Response delete(@PathParam("codigo")int codigo) {
        Departamento departamento = departamentoRepository.getByCodigo(codigo);

        if(departamento == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
            //Caso o usuário informe um valor inexistente retornar 404
        }

        departamentoRepository.delete(departamento);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
