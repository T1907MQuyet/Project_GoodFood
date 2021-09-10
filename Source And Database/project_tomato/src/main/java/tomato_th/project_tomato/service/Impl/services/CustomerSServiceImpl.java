package tomato_th.project_tomato.service.Impl.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tomato_th.project_tomato.model.service.CustomerService;
import tomato_th.project_tomato.repository.service.CustomerServiceRepository;
import tomato_th.project_tomato.service.CustomerServiceService;

import java.util.Date;
import java.util.List;

@Service
public class CustomerSServiceImpl implements CustomerServiceService {
    @Autowired
    private CustomerServiceRepository customerServiceRepository;

    @Override
    public boolean saveCs(CustomerService customerService) {
        try{
            customerService.setCreated(new Date());
            customerService.setUpdated(new Date());
            customerService.setStatus(1);
            customerServiceRepository.save(customerService);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean canceledService(CustomerService customerService) {
        try{
            customerService.setStatus(2);
            customerService.setUpdated(new Date());
            customerServiceRepository.save(customerService);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public CustomerService getById(int custServiceId) {
        try{
            CustomerService customerService = customerServiceRepository.findById(custServiceId).get();
            return customerService;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CustomerService saveCs2(CustomerService customerService) {
        try{
            customerService.setCreated(new Date());
            customerService.setUpdated(new Date());
            customerService.setStatus(0);

            customerServiceRepository.save(customerService);
            return customerService;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<CustomerService> findByCustPaginated(int pageNo, int pageSize,int cust_id) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize, Sort.by("created").descending());
        return this.customerServiceRepository.getCSPaginated(pageable,cust_id);
    }

    @Override
    public Page<CustomerService> findByCustStatusPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);

        return this.customerServiceRepository.getCSStatusPaginated(pageable);
    }

    @Override
    public List<CustomerService> getAllCSByShow() {
        try {
            List<CustomerService> list = customerServiceRepository.getAllCSByShow();
            return list;
        }catch (Exception e)
        {e.printStackTrace();}
        return null;
    }

    @Override
    public List<CustomerService> searhByEmailStatus(String email, int service_id, int status) {
        try{
            List<CustomerService> list = customerServiceRepository.getListByEmailServiceAndStatus(email,service_id,status);
            return list;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CustomerService> getListByStatus(int status) {
        try{
            List<CustomerService> list = customerServiceRepository.getListByStatus(status);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<CustomerService> findByCustPaginatedStatus(int pageNo, int pageSize, int status) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.customerServiceRepository.getByStatusPaginated(status,pageable);
    }

    @Override
    public List<CustomerService> getAllCustomerService() {
        try {
            List<CustomerService> list = customerServiceRepository.findAll();
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<CustomerService> findByCSAll(int service_id, int pageNo, int pageSize) {
        try{
            Pageable pageable = PageRequest.of(pageNo-1,pageSize);
            Page<CustomerService> listPage = customerServiceRepository.getByServiceAll(service_id,1,pageable);
            return listPage;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<CustomerService> findByCSReceivedDate(int service_id, int received_date, int pageNo, int pageSize) {
        try{
            Pageable pageable = PageRequest.of(pageNo-1,pageSize);
            Page<CustomerService> listPage = customerServiceRepository.getByServiceAndReceivedDate(service_id,1,received_date,pageable);
            return listPage;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CustomerService> getListByCSReceivedDate(int service_id) {
        try{
            List<CustomerService> list = customerServiceRepository.getListByServiceAndReceivedDate(service_id,1);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
