package tomato_th.project_tomato.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tomato_th.project_tomato.model.service.Services;

import java.util.List;

public interface ServicesRepository extends JpaRepository<Services, Integer> {
    @Query("SELECT s FROM Services s WHERE s.status=1 OR s.status=2")
    Page<Services> findPaginate(Pageable pageable);

    @Query("SELECT s FROM Services s WHERE s.service_name = ?1")
    Services findByService_name(String service_name);

    @Query("SELECT s FROM Services s WHERE s.status=1")
    List<Services> listByShow();

    @Query("SELECT s FROM Services s WHERE s.status=?1")
    List<Services> listByStatus(int status);
}
