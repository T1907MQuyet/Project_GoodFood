package tomato_th.project_tomato.service;

import tomato_th.project_tomato.model.service.CustomerServiceOrder;

import java.util.Date;
import java.util.List;

public interface CustomerSOService {
    boolean saveCustomerSO(CustomerServiceOrder customerServiceOrder);
    List<CustomerServiceOrder> getAllByCustomerService(int custServiceId);
    List<CustomerServiceOrder> getAllByOrder();
    boolean checkCustomerSO(int custServiceId, Date date);
    List<CustomerServiceOrder> getCSOByServiceId(int service_id);
    List<CustomerServiceOrder> getCSODByReceivedDate(Date received_date);
    boolean checkCustomerSOReceivedDate(int custServiceId,Date date);
}
