package ru.koliadina.spring_mvc.initDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.koliadina.spring_mvc.model.Role;
import ru.koliadina.spring_mvc.model.User;
import ru.koliadina.spring_mvc.service.RoleService;
import ru.koliadina.spring_mvc.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitUsersDB {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public InitUsersDB(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void initApiUserData() {
        Role user = new Role("ROLE_USER");
        roleService.addRole(user);
        Role admin = new Role("ROLE_ADMIN");
        roleService.addRole(admin);

        User user1 = new User("USER", "USER", "Andrey", "Gorbunov",
                "andrey@yandex.com");
        user1.setRoles(Set.of(user));

        Set<Role> roleAdmin = new HashSet<>(roleService.getAllRole());
        User user2 = new User("ADMIN", "ADMIN", "Olga", "Gorbunova",
                "olga@mail.ru");
        user2.setRoles(roleAdmin);

        userService.addUser(user1);
        userService.addUser(user2);
    }
}
