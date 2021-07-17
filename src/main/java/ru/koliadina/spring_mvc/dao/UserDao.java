package ru.koliadina.spring_mvc.dao;

import ru.koliadina.spring_mvc.model.User;

import java.util.List;

public interface UserDao {
     List<User> getAllUsers();
     void addUser(User user);
     User getUserById(long id);
     void deleteUser(long id);
     void updateUser(User user);
     User getUserByLogin(String userLogin);

}
