package tomato_th.project_tomato.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tomato_th.project_tomato.model.News;

import java.util.List;

public interface NewsRepository extends JpaRepository<News,Integer> {
    @Query("SELECT n FROM News n WHERE n.status =1 OR n.status = 2")
    Page<News> findPaginateNewsStatus(Pageable pageable);

    @Query("SELECT n FROM News n WHERE n.status =1")
    Page<News> findPaginateNewsShow(Pageable pageable);

    @Query("SELECT n FROM News n WHERE n.status =1 OR n.status = 2")
    List<News> ListNewsStatus();

    @Query("SELECT n FROM News  n WHERE type_new_id = ?1")
    List<News> listNewsByType(int type_id);

    @Query("SELECT n FROM News n WHERE type_new_id = ?1 AND status = 1")
    Page<News> findPagiListNewByType(int type_id, Pageable pageable);

    @Query("SELECT n FROM News  n WHERE n.home_active=2 ORDER BY n.updated ASC ")
    List<News> listNewsByActiveHomeIndex();

    @Query("SELECT n  FROM News  n WHERE type_new_id = ?1 AND home_active = 2 ORDER BY updated DESC ")
    List<News> listNewsByTypeActiveHome(int type_id, Pageable pageable);

}
