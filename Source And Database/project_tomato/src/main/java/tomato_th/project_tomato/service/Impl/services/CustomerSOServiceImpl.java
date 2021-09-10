package tomato_th.project_tomato.service.Impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tomato_th.project_tomato.model.service.CustomerServiceOrder;
import tomato_th.project_tomato.repository.service.CustomerSORepository;
import tomato_th.project_tomato.service.CustomerSOService;

import java.util.Date;
import java.util.List;

@Service
public class CustomerSOServiceImpl implements CustomerSOService {
    @Autowired
    private CustomerSORepository customerSORepository;

    @Override
    public boolean saveCustomerSO(CustomerServiceOrder customerServiceOrder) {
        try{
            customerServiceOrder.setCreated_date(new Date());
            customerSORepository.save(customerServiceOrder);
            return true;
        }catch (Exception e)
        {e.printStackTrace();}
        return false;
    }

    @Override
    public List<CustomerServiceOrder> getAllByCustomerService(int custServiceId) {
        try{
            List<CustomerServiceOrder> list = customerSORepository.listByCustSId(custServiceId);
            return list;
        }catch (Exception e)
        {e.printStackTrace();}
        return null;
    }

    @Override
    public List<CustomerServiceOrder> getAllByOrder() {
        try{
            List<CustomerServiceOrder> list = customerSORepository.findAll();
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkCustomerSO(int custServiceId, Date date) {
        try{
           List<CustomerServiceOrder> customerServiceOrderList = customerSORepository.geyCustomerSO(custServiceId,date);
           if (customerServiceOrderList.size()<=0)
           {
               return false;
           }
           CustomerServiceOrder customerServiceOrder = customerServiceOrderList.get(0);
            if (customerServiceOrder==null)
            {
                return false;
            }
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CustomerServiceOrder> getCSOByServiceId(int service_id) {
        try{
            List<CustomerServiceOrder> list = customerSORepository.getListCSOByServiceId(service_id);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CustomerServiceOrder> getCSODByReceivedDate(Date received_date) {
        try{
            List<CustomerServiceOrder> list = customerSORepository.getCustOdReceivedDate(received_date);
            return list;

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkCustomerSOReceivedDate(int custServiceId, Date date) {
        try{
            List<CustomerServiceOrder> customerServiceOrderList = customerSORepository.getCSOByCSIdAndReceivedDate(custServiceId,date);
            if (customerServiceOrderList.size()<=0) { return false; }
            CustomerServiceOrder customerServiceOrder = customerServiceOrderList.get(0);
            if (customerServiceOrder==null) { return false; }
            return true;
        }catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}
