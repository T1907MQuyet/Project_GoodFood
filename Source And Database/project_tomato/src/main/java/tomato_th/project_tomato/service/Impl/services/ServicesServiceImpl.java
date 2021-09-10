package tomato_th.project_tomato.service.Impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tomato_th.project_tomato.model.service.Services;
import tomato_th.project_tomato.repository.service.ServicesRepository;
import tomato_th.project_tomato.service.ServicesService;

import java.util.Date;
import java.util.List;

@Service
public class ServicesServiceImpl implements ServicesService {
    @Autowired
    private ServicesRepository servicesRepository;

    @Override
    public List<Services> listService() {
        try {
            List<Services> list = servicesRepository.findAll();
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Services> listServiceShow() {
        try{
            List<Services> list = servicesRepository.listByShow();
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Services> listServiceByStatus(int status) {
        try{
            List<Services> list = servicesRepository.listByStatus(status);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int countServiceTrash() {
        try{
            List<Services> list = servicesRepository.listByStatus(3);
            return list.size();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Services getService(int id) {
        try{
            Services services = servicesRepository.findById(id).get();
            return  services;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveService(Services services) {
        try{
            services.setCreated(new Date());
//            services.setStatus(1);
            services.setUpdated(new Date());
            servicesRepository.save(services);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateService(Services services) {
        try{
            servicesRepository.findById(services.getService_id()).get();
            services.setUpdated(new Date());
            servicesRepository.save(services);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteService(int id) {
        try{
            Services services = servicesRepository.findById(id).get();
            services.setUpdated(new Date());
            services.setStatus(3);
            servicesRepository.save(services);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkServiceName(String service_name) {
       Services services = servicesRepository.findByService_name(service_name);
       if (services==null)
       {
           return true;
       }else{
           return false;
       }
    }

    @Override
    public Page<Services> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.servicesRepository.findPaginate(pageable);
    }

    public int countTrashService()
    {
        try{
            List<Services> list = servicesRepository.listByStatus(3);
            return list.size();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }
}
