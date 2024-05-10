package edu.uestc.imdds.service;

import edu.uestc.imdds.entitiy.User;

import java.util.List;

public interface UserService {

    User getById(Integer id);

    User getByName(String name);

    Boolean addUser(User user);

    Boolean deleteById(Integer id);

    Boolean updateUser(User user);

    List<User> getAll();

    User login(String username, String password);

}
