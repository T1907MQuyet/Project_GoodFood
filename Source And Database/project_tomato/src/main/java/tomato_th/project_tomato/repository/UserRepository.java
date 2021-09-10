package tomato_th.project_tomato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tomato_th.project_tomato.model.User;


public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String username);
}
