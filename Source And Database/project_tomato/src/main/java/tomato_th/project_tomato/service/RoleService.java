package tomato_th.project_tomato.service;

import tomato_th.project_tomato.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRole();
    Role insertRole(Role role);
    Role getRoleById(int id);
    boolean update(Role role);
    boolean saveRole(Role role);
}
