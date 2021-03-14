package org.example.project.api.funcionarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public class FuncionarioRequest {
    @NotNull //Campo não pode ser nulo
    @NotBlank //Caso o usuário digitei espaço "    " ele não vai aceitar
    @Size(min = 2, max = 100) //limite de caracteres
    public String nome;

    //As anotações sempre ficam encima dos atríbutos
    @NotNull @Email
    public String email;

    @NotNull @NotBlank @Size(min = 1, max = 11)
    @CPF
    //@Columns
    public String cpf;
}
