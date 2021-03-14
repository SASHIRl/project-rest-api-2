package org.example.project.api.departamentos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DepartamentoRequest {
    @NotNull //Campo não pode ser nulo
    @NotBlank //Caso o usuário digitei espaço "    " ele não vai aceitar
    @Size(min = 2, max = 100) //limite de caracteres
    public String nome;

    //As anotações sempre ficam encima dos atríbutos
    @NotNull @NotBlank @Size(min = 2, max = 10)
    public String sigla;
}
