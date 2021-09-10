package tomato_th.project_tomato.service;

import tomato_th.project_tomato.model.service.Calender;

import java.util.List;

public interface CalenderService {
    List<Calender> listCalenderByService(int service_id);
    List<Calender> listCalenByServiceStatus(int service_id,int status);
    List<Calender> getAllCalenBystatus(int status);
    Calender getCalenderById(int id);
    boolean saveCalender(Calender calender);
    boolean hiddenCalender(int id);
    boolean showCalender(int id);
    boolean deleteCalender(int id);
}
