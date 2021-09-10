package tomato_th.project_tomato.repository.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tomato_th.project_tomato.model.service.Calender;

import java.util.List;

public interface CalenderRepository extends JpaRepository<Calender,Integer> {
    @Query("SELECT c FROM Calender c WHERE c.services.service_id=?1 AND c.status=1 OR c.services.service_id=?1 AND c.status=2 ")
    List<Calender> listCalenderByService(int calender_id);

    @Query("SELECT c FROM Calender c WHERE c.services.service_id=?1 AND c.status=?2 ORDER BY c.lunar_calendar ASC ")
    List<Calender> listCalenByServiceStatus(int service_id,int status);

    @Query("SELECT c FROM Calender c WHERE c.status=?1")
    List<Calender> listCalenderByStatus(int status);

}
