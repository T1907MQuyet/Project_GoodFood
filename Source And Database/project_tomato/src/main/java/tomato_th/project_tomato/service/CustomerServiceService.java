package tomato_th.project_tomato.service;

import org.springframework.data.domain.Page;
import tomato_th.project_tomato.model.service.CustomerService;

import java.util.List;

public interface CustomerServiceService {
    boolean saveCs(CustomerService customerService);
    boolean canceledService(CustomerService customerService);
    CustomerService getById(int custServiceId);
    CustomerService saveCs2(CustomerService customerService);
    Page<CustomerService> findByCustPaginated(int pageNo, int pageSize,int cust_id);
    Page<CustomerService> findByCustStatusPaginated(int pageNo,int pageSize);
    List<CustomerService> getAllCSByShow();
    List<CustomerService> searhByEmailStatus(String email,int service_id,int status);
    List<CustomerService> getListByStatus(int status);
    Page<CustomerService> findByCustPaginatedStatus(int pageNo,int pageSize,int status);
    List<CustomerService> getAllCustomerService();
    Page<CustomerService> findByCSAll(int service_id,int pageNo,int pageSize);
    Page<CustomerService> findByCSReceivedDate(int service_id,int received_date,int pageNo,int pageSize);
    List<CustomerService> getListByCSReceivedDate(int service_id);

}
