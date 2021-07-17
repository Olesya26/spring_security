package ru.koliadina.spring_mvc.service;

import ru.koliadina.spring_mvc.model.Role;
import ru.koliadina.spring_mvc.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void addUser(User user);
    User getUserById(long id);
    void deleteUser(long id);
    void updateUser(User user);
    User getUserByLogin(String userLogin);

}
