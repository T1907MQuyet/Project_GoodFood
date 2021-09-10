package tomato_th.project_tomato.service.Impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tomato_th.project_tomato.model.service.Calender;
import tomato_th.project_tomato.repository.service.CalenderRepository;
import tomato_th.project_tomato.service.CalenderService;

import java.util.Date;
import java.util.List;

@Service
public class CalenderServiceImpl implements CalenderService {
    @Autowired
    private CalenderRepository calenderRepository;
    @Override
    public List<Calender> listCalenderByService(int service_id) {
        try{
            List<Calender> list = calenderRepository.listCalenderByService(service_id);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Calender> listCalenByServiceStatus(int service_id, int status) {
        try{
            List<Calender> list = calenderRepository.listCalenByServiceStatus(service_id,status);
            if (list.size()>0)
            {
                return list;

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Calender> getAllCalenBystatus(int status) {
        try{
            List<Calender> list = calenderRepository.listCalenderByStatus(status);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Calender getCalenderById(int id) {
        try{
            Calender calender = calenderRepository.findById(id).get();
            return calender;
        }catch (Exception e)
        {e.printStackTrace();}
        return null;
    }

    @Override
    public boolean saveCalender(Calender calender) {
        try{
            calender.setCreated(new Date());
            calender.setUpdated(new Date());
            calender.setStatus(1);
            calenderRepository.save(calender);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean hiddenCalender(int id) {
        try{
            Calender calender = calenderRepository.findById(id).get();
            calender.setUpdated(new Date());
            calender.setStatus(2);
            calenderRepository.save(calender);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean showCalender(int id) {
        try{
            Calender calender = calenderRepository.findById(id).get();
            calender.setUpdated(new Date());
            calender.setStatus(1);
            calenderRepository.save(calender);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCalender(int id) {
        try{
            Calender calender = calenderRepository.findById(id).get();
            calender.setUpdated(new Date());
            calender.setStatus(3);
            calenderRepository.save(calender);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
