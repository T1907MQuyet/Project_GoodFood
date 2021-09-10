package tomato_th.project_tomato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tomato_th.project_tomato.model.Role;


public interface RoleRepository extends JpaRepository<Role,Integer> {
}
