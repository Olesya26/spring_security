package ru.koliadina.spring_mvc.initDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.koliadina.spring_mvc.model.User;
import ru.koliadina.spring_mvc.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class InitUsersDB {

    private UserService userService;

    @Autowired
    public InitUsersDB(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initApiUserData() {
        User user1 = new User("Andrey","Gorbunov","andrey@yandex.com");
        User user2 = new User("Olga","Gorbunova","olga@mail.ru");
        userService.addUser(user1);
        userService.addUser(user2);
    }
}
