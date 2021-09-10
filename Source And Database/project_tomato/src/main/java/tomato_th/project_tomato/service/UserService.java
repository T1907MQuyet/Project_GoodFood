package tomato_th.project_tomato.service;





import tomato_th.project_tomato.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User findUserById(int id);
    User findByUsername(String email);
    Boolean saveUser2(User user);
    boolean updatePassword(int id, String pwd);
    List<User> getAllUser();
}
