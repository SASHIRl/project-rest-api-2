package org.example.project.api.projetos;

import jakarta.validation.constraints.*;

import java.util.Date;

public class ProjetoRequest {
    @NotNull //Campo não pode ser nulo
    @NotBlank //Caso o usuário digitei espaço "    " ele não vai aceitar
    @Size(min = 2, max = 100) //limite de caracteres
    public String titulo;

    @NotNull //Campo não pode ser nulo
    @FutureOrPresent
    public Date data_inicio;

    @NotNull //Campo não pode ser nulo
    @Future
    public Date data_fim;

    @NotNull //Campo não pode ser nulo
    @NotBlank //Caso o usuário digitei espaço "    " ele não vai aceitar
    @Size(min = 1, max = 100) //limite de caracteres
    public String codigo_departamento;
}
