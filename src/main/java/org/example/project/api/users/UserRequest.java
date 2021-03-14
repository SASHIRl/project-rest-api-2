package org.example.project.api.users;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

public class UserRequest {
  @NotNull //Campo não pode ser nulo 
  @NotBlank //Caso o usuário digitei espaço "    " ele não vai aceitar
  @Size(min = 2, max = 100) //limite de caracteres
  public String name;

  //As anotações sempre ficam encima dos atríbutos
  @NotNull @Email
  public String email;

  @NotNull @NotBlank @Size(min = 6, max = 100)
  public String password;
}