package org.example.project.api.departamentos;

import java.util.ArrayList;
import java.util.List;

public class DepartamentoRepository {
    private List<Departamento> departamentos = new ArrayList<>();

    public Departamento save(Departamento departamento) {
        departamentos.add(departamento);
        return departamento;
    }

    public List<Departamento> getAll() {
        return departamentos;
    }

    public Departamento getByCodigo(int codigo) {

  /*for (User user : users) {
    if (user.getId == id) {
      return user;
    }
  }
    return null;*/

        //Filtra todos os usuários, onde um usuário tem o getId == id
        return departamentos.stream().filter(departamento -> departamento.getCodigo() == codigo).findAny(/*encontre qualquer valor*/).orElse(null);
        //Caso contrário devolve null
    }

    public Departamento update(Departamento departamento) {
        Departamento departamentoFound = getByCodigo(departamento.getCodigo());
        departamentos.set(departamentos.indexOf(departamentoFound), departamento);
        return departamento;
    }

    public void delete(Departamento departamento) {
        departamentos.remove(departamento);
    }

}
