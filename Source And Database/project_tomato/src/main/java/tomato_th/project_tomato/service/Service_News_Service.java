package tomato_th.project_tomato.service;

import tomato_th.project_tomato.model.service.Service_News;

import java.util.List;

public interface Service_News_Service {
    boolean saveServiceNew(Service_News service_news);
    boolean updateServiceNew(Service_News service_news);
    boolean deleteServiceNew(int id);
    Service_News getServiceNewByServiceId(int service_id);
    Service_News getServiceNewByNewId(int new_id);
    List<Service_News> getListServiceNewByNewId(int new_id);
    List<Service_News> getListServiceNewByServiceId(int service_id);
}
