package org.example.project.api.funcionarios;

public class FuncionarioResponse {
    public int codigo;
    public String nome;
    public String email;
    public String cpf;

    public FuncionarioResponse(int codigo, String nome, String email, String cpf) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public FuncionarioResponse(Funcionario funcionario) {
        this.codigo = funcionario.getCodigo();
        this.nome = funcionario.getNome();
        this.email = funcionario.getEmail();
        this.cpf = funcionario.getCpf();
    }
}
