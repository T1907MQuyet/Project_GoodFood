package tomato_th.project_tomato.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tomato_th.project_tomato.model.Banners;

import java.util.List;

public interface BannersRepository extends JpaRepository<Banners,Integer> {
    @Query("SELECT b FROM Banners b WHERE b.status=1 OR b.status=2 ORDER BY b.status ASC")
    Page<Banners> getAllBannerPaginated(Pageable pageable);

    @Query("SELECT b FROM Banners b WHERE b.type_banner.type_name=?1 AND b.status=1")
    List<Banners> getBannerByBannerName(String banner_name);

}
