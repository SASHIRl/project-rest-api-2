package org.example.project.api.users;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
  private List<User> users = new ArrayList<>();

  public User save(User user) {
    users.add(user);
    return user;
  }

  public List<User> getAll() {
    return users;
  }

  public User getById(int id) {

  /*for (User user : users) {
    if (user.getId == id) {
      return user;
    }
  }
    return null;*/

    //Filtra todos os usuários, onde um usuário tem o getId == id
    return users.stream().filter(user -> user.getId() == id).findAny(/*encontre qualquer valor*/).orElse(null);
                                                                                  //Caso contrário devolve null
  }

  public User update(User user) {
    User userFound = getById(user.getId());
    users.set(users.indexOf(userFound), user);
    return user;
  }

  public void delete(User user) {
    users.remove(user);
  }

}