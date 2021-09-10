package tomato_th.project_tomato.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tomato_th.project_tomato.model.User_roles;
import tomato_th.project_tomato.repository.UserRoleRepository;
import tomato_th.project_tomato.service.UserRoleService;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Override
    public List<User_roles> getAllRole() {
        try{
            List<User_roles> list = userRoleRepository.findAll();
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveRole(User_roles user_roles) {
        try{
            userRoleRepository.save(user_roles);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User_roles getRoleById(int id) {
        try{
            User_roles user_roles = userRoleRepository.findById(id).get();
            return user_roles;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean editRole(User_roles user_roles) {
        try{
            userRoleRepository.save(user_roles);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteRole(int id) {
        try{
            userRoleRepository.deleteById(id);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
