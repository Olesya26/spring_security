package ru.koliadina.spring_mvc.dao;


import antlr.BaseAST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.koliadina.spring_mvc.model.Role;
import ru.koliadina.spring_mvc.model.User;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserByLogin(String login) {
        return entityManager.createQuery("select user from User user where user.userLogin =:userLogin", User.class)
                .setParameter("userLogin", login)
                .getSingleResult();
    }
}
