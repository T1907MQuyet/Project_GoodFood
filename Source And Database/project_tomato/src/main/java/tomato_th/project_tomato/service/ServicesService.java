package tomato_th.project_tomato.service;


import org.springframework.data.domain.Page;
import tomato_th.project_tomato.model.service.Services;

import java.util.List;

public interface ServicesService {
    List<Services> listService();
    List<Services> listServiceShow();
    List<Services> listServiceByStatus(int status);
    int countServiceTrash();
    Services getService(int id);
    boolean saveService(Services services);
    boolean updateService(Services services);
    boolean deleteService(int id);
    boolean checkServiceName(String service_name);
    Page<Services> findPaginated(int pageNo, int pageSize);

}
