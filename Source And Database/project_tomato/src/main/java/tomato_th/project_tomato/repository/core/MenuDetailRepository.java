package tomato_th.project_tomato.repository.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tomato_th.project_tomato.model.core.Menu_detail;

import java.util.List;

public interface MenuDetailRepository extends JpaRepository<Menu_detail,Integer> {
    List<Menu_detail> findAllByStatus(int status);
    @Query("SELECT m FROM Menu_detail m WHERE  menu_id = ?1")
    List<Menu_detail> getByMenuId(int menu_id);
    @Query("SELECT c FROM Menu_detail c WHERE status = 1 OR status = 2")
    List<Menu_detail> getAllMenuStatus();

    @Query("SELECT c FROM Menu_detail c WHERE status = 1 OR status = 2")
    Page<Menu_detail> findPaginateMenuStatus(Pageable pageable);

    @Query("SELECT  m FROM Menu_detail m WHERE menu_detail_name = ?1 AND menu_id = ?2")
    Menu_detail findByMenuDetailName(String menuDName, int menu_id);

}
