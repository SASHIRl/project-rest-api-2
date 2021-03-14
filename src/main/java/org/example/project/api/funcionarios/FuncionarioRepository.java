package org.example.project.api.funcionarios;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository {

    private List<Funcionario> funcionarios = new ArrayList<>();

    public Funcionario save(Funcionario funcionario) {
        for (int count = 0; count < funcionarios.size(); count++) {
            if (funcionarios.equals(funcionario.getCpf())) {
                System.out.println("CPF já existente!");
            } else
                funcionarios.add(funcionario);
        }
        return funcionario;
    }

    public List<Funcionario> getAll() {
        return funcionarios;
    }

    public Funcionario getByCodigo(int codigo) {

  /*for (Funcionario funcionario : funcionarios) {
    if (funcionario.getCodigo == codigo) {
      return funcionario;
    }
  }
    return null;*/

        //Filtra todos os funcionarios, onde um funcionario tem o getCodigo == codigo
        return funcionarios.stream().filter(funcionario -> funcionario.getCodigo() == codigo).findAny(/*encontre qualquer valor*/).orElse(null);
        //Caso contrário devolve null
    }

    public Funcionario update(Funcionario funcionario) {
        Funcionario funcionarioFound = getByCodigo(funcionario.getCodigo());
        funcionarios.set(funcionarios.indexOf(funcionarioFound), funcionario);
        return funcionario;
    }

    public void delete(Funcionario funcionario) {
        funcionarios.remove(funcionario);
    }

}
