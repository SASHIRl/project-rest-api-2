package org.example.project.api.projetos;

import java.util.ArrayList;
import java.util.List;

public class ProjetoRepository {

    private List<Projeto> projetos = new ArrayList<>();

    public Projeto save(Projeto projeto) {
        projetos.add(projeto);
        return projeto;
    }

    public List<Projeto> getAll() {
        return projetos;
    }

    public Projeto getByCodigo(int codigo) {

  /*for (User user : users) {
    if (user.getId == id) {
      return user;
    }
  }
    return null;*/

        //Filtra todos os usuários, onde um usuário tem o getId == id
        return projetos.stream().filter(projeto -> projeto.getCodigo() == codigo).findAny(/*encontre qualquer valor*/).orElse(null);
        //Caso contrário devolve null
    }

    public Projeto update(Projeto projeto) {
        Projeto projetoFound = getByCodigo(projeto.getCodigo());
        projetos.set(projetos.indexOf(projetoFound), projeto);
        return projeto;
    }

    public void delete(Projeto projeto) {
        projetos.remove(projeto);
    }

}
