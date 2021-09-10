package tomato_th.project_tomato.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tomato_th.project_tomato.model.TypeNews;

import java.util.List;

public interface TypeNewsRepository extends JpaRepository<TypeNews,Integer> {
    @Query("SELECT t from TypeNews t WHERE status=1 OR status =2")
    Page<TypeNews> findPaginate(Pageable pageable);
    List<TypeNews> findAllByStatus(int status);
}
