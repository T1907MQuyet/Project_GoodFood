package tomato_th.project_tomato.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tomato_th.project_tomato.model.User;
import tomato_th.project_tomato.repository.UserRepository;
import tomato_th.project_tomato.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public User saveUser(User user) {
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return user;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findUserById(int id) {
        try{
            User user = userRepository.findById(id).get();
            return user;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findByUsername(String email) {
        try{
            User user = userRepository.findByEmail(email);
            return user;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean saveUser2(User user) {
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updatePassword(int id, String pwd) {
        try{
            User user = userRepository.getById(id);
            user.setPassword(passwordEncoder.encode(pwd));
            userRepository.save(user);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> getAllUser() {
        try{
            List<User> listUser = userRepository.findAll();
            return listUser;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
