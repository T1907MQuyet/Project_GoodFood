package tomato_th.project_tomato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tomato_th.project_tomato.model.Type_Banner;

import java.util.List;

public interface TypeBannerRepository extends JpaRepository<Type_Banner,Integer> {
    @Query("SELECT t FROM Type_Banner t WHERE t.status=1 OR t.status=2 ORDER BY t.status ASC ")
    List<Type_Banner> getListTypeBannerShowAndHidden();

    @Query("SELECT t FROM Type_Banner t WHERE t.status =?1")
    List<Type_Banner> getTypeBannerStatus(int status);
}
