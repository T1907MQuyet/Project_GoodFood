package tomato_th.project_tomato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tomato_th.project_tomato.model.User_roles;


public interface UserRoleRepository extends JpaRepository<User_roles, Integer> {
}
