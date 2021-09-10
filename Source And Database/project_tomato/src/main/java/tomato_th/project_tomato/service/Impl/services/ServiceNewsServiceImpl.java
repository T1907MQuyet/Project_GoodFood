package tomato_th.project_tomato.service.Impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tomato_th.project_tomato.model.service.Service_News;
import tomato_th.project_tomato.repository.service.Service_News_Repository;
import tomato_th.project_tomato.service.Service_News_Service;

import java.util.Date;
import java.util.List;

@Service
public class ServiceNewsServiceImpl implements Service_News_Service {
    @Autowired
    private Service_News_Repository service_news_repository;

    @Override
    public boolean saveServiceNew(Service_News service_news) {
        try{
            service_news.setCreated(new Date());
            service_news.setUpdated(new Date());
            service_news_repository.save(service_news);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateServiceNew(Service_News service_news) {
        try{
            service_news.setUpdated(new Date());
            service_news_repository.save(service_news);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteServiceNew(int id) {
        try{
            Service_News service_news = service_news_repository.findById(id).get();
            service_news_repository.deleteById(id);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Service_News getServiceNewByServiceId(int service_id) {
        try{
            List<Service_News> list = service_news_repository.getServiceNewByService(service_id);
            Service_News service_news = list.get(0);
            return service_news;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Service_News getServiceNewByNewId(int new_id) {
        try{
            List<Service_News> list = service_news_repository.getServiceNewByNewId(new_id);
            Service_News service_news = list.get(0);
            return service_news;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Service_News> getListServiceNewByNewId(int new_id) {
        try{
            List<Service_News> list =service_news_repository.getServiceNewByNewId(new_id);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Service_News> getListServiceNewByServiceId(int service_id) {
        try{
            List<Service_News> list = service_news_repository.getServiceNewByService(service_id);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
