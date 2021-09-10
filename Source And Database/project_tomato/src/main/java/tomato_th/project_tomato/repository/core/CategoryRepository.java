package tomato_th.project_tomato.repository.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tomato_th.project_tomato.model.core.Category;


import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    List<Category> findAllByStatus(int status);

    @Query("SELECT c FROM Category c WHERE cate_name = ?1")
    Category findByCate_name(String cate_name);

    @Query("SELECT c FROM Category c WHERE status = 1 OR status = 2")
    List<Category> findActiveCateStatus();

    @Query("SELECT c FROM Category c WHERE status = 1 OR status = 2")
    Page<Category> findPaginateCateStatus(Pageable pageable);
    @Query("SELECT c FROM Category c WHERE c.cate_name LIKE %:cate_name% AND status = 1 OR status=2")
    List<Category> getListCateByName(@Param("cate_name") String cate_name);
}
