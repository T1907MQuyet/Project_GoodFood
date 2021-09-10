package tomato_th.project_tomato.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tomato_th.project_tomato.model.Role;
import tomato_th.project_tomato.repository.RoleRepository;
import tomato_th.project_tomato.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRole() {
        try{
            List<Role> listRole = roleRepository.findAll();
            return  listRole;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Role insertRole(Role role) {
        try{
            roleRepository.save(role);
            return role;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Role getRoleById(int id) {
        try{
            Role role = roleRepository.findById(id).get();
            return  role;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Role role) {
        try{
            roleRepository.save(role);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveRole(Role role) {
        try{
            roleRepository.save(role);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
