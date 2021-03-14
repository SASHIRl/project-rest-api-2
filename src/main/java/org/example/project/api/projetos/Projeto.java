package org.example.project.api.projetos;

import java.util.Date;

public class Projeto {

    private String titulo;
    private Date data_inicio;
    private Date data_fim;
    private int codigo;
    private String codigo_departamento;
    private static int countId = 1;

    public Projeto (String titulo, Date data_inicio, Date data_fim, String codigo_departamento) {
        this.codigo = countId++;
        this.titulo = titulo;
        this.codigo_departamento = codigo_departamento;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
    }

    public Projeto (String titulo, Date data_inicio, Date data_fim, int codigo, String codigo_departamento) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.codigo_departamento = codigo_departamento;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
    }

    public String getTitulo() {
        return titulo;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public Date getData_fim() {
        return data_fim;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getCodigo_departamento() {
        return codigo_departamento;
    }
}