package org.example.project.api.projetos;

import java.util.Date;

public class ProjetoResponse {
    public String titulo;
    public Date data_inicio;
    public Date data_fim;
    public int codigo;
    public String codigo_departamento;

    public ProjetoResponse(String codigo_departamento, int codigo, String titulo, Date data_inicio, Date data_fim) {
        this.codigo_departamento = codigo_departamento;
        this.codigo = codigo;
        this.titulo = titulo;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
    }

    public ProjetoResponse(Projeto projeto) {
        this.codigo = projeto.getCodigo();
        this.titulo = projeto.getTitulo();
        this.data_inicio = projeto.getData_inicio();
        this.data_fim = projeto.getData_fim();
        this.codigo_departamento = projeto.getCodigo_departamento();
    }
}
