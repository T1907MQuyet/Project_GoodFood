package tomato_th.project_tomato.repository.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tomato_th.project_tomato.model.service.Service_News;

import java.util.List;

public interface Service_News_Repository extends JpaRepository<Service_News,Integer> {

    @Query("SELECT s FROM Service_News s WHERE s.services.service_id=?1 ORDER BY s.updated DESC ")
    List<Service_News> getServiceNewByService(int service_id);

    @Query("SELECT s FROM Service_News s WHERE s.news.news_id=?1  ")
    List<Service_News> getServiceNewByNewId(int news_id);

    @Query("SELECT s FROM Service_News s WHERE s.news.news_id=?1")
    Service_News getServiceNewByNew(int news_id);
}
