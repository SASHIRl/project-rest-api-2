package org.example.project.api.users;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.util.List;
import java.util.stream.Collectors;

//Quando /api/users for acessado irá cair nesta classe
@Path("users")
public class UserResource {
  // CRUD - CREATE, READ, UPDATE, DELETE

  private static UserRepository userRepository = new UserRepository();


  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response create(@Valid UserRequest request) {
    User user = new User(request.name, request.email, request.password);
    userRepository.save(user);
    return Response.status(Status.CREATED)
    .entity(new UserResponse(user.getId(), user.getName(), user.getEmail()))
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

    List<UserResponse> userResponses = userRepository.getAll().stream()
    .map(UserResponse :: new)
    .collect(Collectors.toList());

    return Response.status(Status.OK).entity(userResponses).build();
  }

  //
  @GET
  @Path("{id}")//Sempre que for colocado entre chaves em uma definição de @Path, ele vai vir como váriavel.
  //show para pegar um só, padronização.
  @Produces(MediaType.APPLICATION_JSON)
  public Response show(@PathParam ("id"/*Colocar o mesmo nome definido em @Path*/)int id) {
    User user = userRepository.getById(id);

    if(user == null) {
      return Response.status(Status.NOT_FOUND).build();
      //Caso o usuário informe um valor inexistente retornar 404
    }

    return Response.status(Status.OK).entity(new UserResponse(user)).build();
  }
  @PUT
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response update(@PathParam("id") int id, @Valid UserRequest request) {
    User user = userRepository.getById(id);

    if(user == null) {
      return Response.status(Status.NOT_FOUND).build();
      //Caso o usuário informe um valor inexistente retornar 404
    }

    User newUser = new User(id, request.name, request.email, request.password);
    userRepository.update(newUser);
    return Response.status(Status.OK).entity(new UserResponse(newUser)).build();
  }


  @DELETE
  @Path("{id}") 
  public Response delete(@PathParam("id")int id) {
    User user = userRepository.getById(id);

    if(user == null) {
      return Response.status(Status.NOT_FOUND).build();
      //Caso o usuário informe um valor inexistente retornar 404
    }

    userRepository.delete(user);
    return Response.status(Status.NO_CONTENT).build();
  }

}