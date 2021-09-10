package tomato_th.project_tomato.service.Impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tomato_th.project_tomato.model.service.CustomerServiceProduct;
import tomato_th.project_tomato.repository.service.CustomerSPRepository;
import tomato_th.project_tomato.service.CustomerSPService;

import java.util.Date;
import java.util.List;

@Service
public class CustomerSPServiceImpl implements CustomerSPService {
    @Autowired
    CustomerSPRepository customerSPRepository;

    @Override
    public boolean saveCustomerSPro(CustomerServiceProduct customerServiceProduct) {
        try{
            customerServiceProduct.setCreated(new Date());
            customerServiceProduct.setUpdated(new Date());
            customerServiceProduct.setStatus(1);
            customerSPRepository.save(customerServiceProduct);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCustomerSPro(CustomerServiceProduct customerServiceProduct) {
        try{
            customerServiceProduct.setUpdated(new Date());
            customerSPRepository.save(customerServiceProduct);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CustomerServiceProduct> getByCustomerService(int custServiceId) {
        try{
            List<CustomerServiceProduct> list = customerSPRepository.getCustSPByCustomerService(custServiceId);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CustomerServiceProduct> getByCustomerServiceStatus(int custServiceId, int status) {
        try{
            List<CustomerServiceProduct> list = customerSPRepository.getCustSPByCustSStatus(custServiceId,status);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CustomerServiceProduct> getBtCustomerServiceByCSPId(int customer_service_id) {
        try{
            List<CustomerServiceProduct> list = customerSPRepository.getCustomerServiceProductByCSPId(customer_service_id);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
