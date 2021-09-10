package tomato_th.project_tomato.service;



import tomato_th.project_tomato.model.User_roles;

import java.util.List;

public interface UserRoleService {
    List<User_roles> getAllRole();
    boolean saveRole(User_roles user_roles);
    User_roles getRoleById(int id);
    boolean editRole(User_roles user_roles);
    boolean deleteRole(int id);
}
