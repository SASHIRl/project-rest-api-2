package org.example.project.api.funcionarios;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("funcionarios")
public class FuncionarioResource {
    // CRUD - CREATE, READ, UPDATE, DELETE

    private static FuncionarioRepository funcionarioRepository = new FuncionarioRepository();


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid FuncionarioRequest request) {
        Funcionario funcionario = new Funcionario(request.nome, request.email, request.cpf);
        funcionarioRepository.save(funcionario);
        return Response.status(Response.Status.CREATED)
                .entity(new FuncionarioResponse(funcionario.getCodigo(), funcionario.getNome(), funcionario.getEmail(), funcionario.getCpf()))
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

        List<FuncionarioResponse> funcionarioResponses = funcionarioRepository.getAll().stream()
                .map(FuncionarioResponse :: new)
                .collect(Collectors.toList());

        return Response.status(Response.Status.OK).entity(funcionarioResponses).build();
    }

    //
    @GET
    @Path("{codigo}")//Sempre que for colocado entre chaves em uma definição de @Path, ele vai vir como váriavel.
    //show para pegar um só, padronização.
    @Produces(MediaType.APPLICATION_JSON)
    public Response show(@PathParam ("codigo"/*Colocar o mesmo nome definido em @Path*/)int codigo) {
        Funcionario funcionario = funcionarioRepository.getByCodigo(codigo);

        if(funcionario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
            //Caso o usuário informe um valor inexistente retornar 404
        }

        return Response.status(Response.Status.OK).entity(new FuncionarioResponse(funcionario)).build();
    }
    @PUT
    @Path("{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("codigo") int codigo, @Valid FuncionarioRequest request) {
        Funcionario funcionario = funcionarioRepository.getByCodigo(codigo);

        if(funcionario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
            //Caso o usuário informe um valor inexistente retornar 404
        }

        Funcionario newFuncionario = new Funcionario(codigo, request.nome, request.email, request.cpf);
        funcionarioRepository.update(newFuncionario);
        return Response.status(Response.Status.OK).entity(new FuncionarioResponse(newFuncionario)).build();
    }


    @DELETE
    @Path("{codigo}")
    public Response delete(@PathParam("codigo")int codigo) {
        Funcionario funcionario = funcionarioRepository.getByCodigo(codigo);

        if(funcionario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
            //Caso o usuário informe um valor inexistente retornar 404
        }

        funcionarioRepository.delete(funcionario);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
