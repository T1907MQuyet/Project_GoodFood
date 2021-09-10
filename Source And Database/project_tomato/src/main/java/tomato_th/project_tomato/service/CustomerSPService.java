package tomato_th.project_tomato.service;

import tomato_th.project_tomato.model.service.CustomerServiceProduct;

import java.util.List;

public interface CustomerSPService {
    boolean saveCustomerSPro(CustomerServiceProduct customerServiceProduct);
    boolean updateCustomerSPro(CustomerServiceProduct customerServiceProduct);
    List<CustomerServiceProduct> getByCustomerService(int custServiceId);
    List<CustomerServiceProduct> getByCustomerServiceStatus(int custServiceId,int status);
    List<CustomerServiceProduct> getBtCustomerServiceByCSPId(int customer_service_id);
}
