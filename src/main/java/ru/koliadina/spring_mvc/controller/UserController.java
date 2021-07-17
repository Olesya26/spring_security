package ru.koliadina.spring_mvc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.koliadina.spring_mvc.model.Role;
import ru.koliadina.spring_mvc.model.User;
import ru.koliadina.spring_mvc.service.RoleService;
import ru.koliadina.spring_mvc.service.UserService;

import java.util.HashSet;
import java.util.Set;


@Controller
public class UserController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/")
    public String get(){
        return "hello";
    }

    @GetMapping(value = "/user")
    public String getUserInfo(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles",user.getRoles());
        return "userInfo";
    }

    @GetMapping(value = "/admin")
    public String getAllUsers(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "adminInfo";
    }

    @GetMapping(value = "/admin/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRole());
        return "new";
    }

    @PostMapping(value = "/admin/add")
    private String addUser(@ModelAttribute User user, @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles) {
        Set<Role> roles = new HashSet<>();
        for (String role : checkBoxRoles) {
            roles.add(roleService.getRoleByName(role));
        }
        user.setRoles(roles);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PutMapping(value = "{id}")
    public String updateUser(@ModelAttribute User user, @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles) {
        Set<Role> roles = new HashSet<>();
        for (String role : checkBoxRoles) {
            roles.add(roleService.getRoleByName(role));
        }
        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/edit/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("role",roleService.getAllRole());
        return "edit";
    }

    @DeleteMapping(value = "/remove/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
