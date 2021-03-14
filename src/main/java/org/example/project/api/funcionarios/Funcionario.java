package org.example.project.api.funcionarios;

public class Funcionario {
    private int codigo;
    private String cpf;
    private String nome;
    private String email;
    private static int countId = 1;

    public Funcionario (String nome, String email, String cpf) {
        this.codigo = countId++;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public Funcionario (int codigo, String nome, String email, String cpf) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }
}
