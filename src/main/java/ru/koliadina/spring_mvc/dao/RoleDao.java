package ru.koliadina.spring_mvc.dao;

import ru.koliadina.spring_mvc.model.Role;

import java.util.List;

public interface RoleDao {
    List<Role> getAllRole();
    void addRole(Role role);
    void updateRole(Role role);
    void deleteRole(long id);
    Role getRoleByName(String name);
}
