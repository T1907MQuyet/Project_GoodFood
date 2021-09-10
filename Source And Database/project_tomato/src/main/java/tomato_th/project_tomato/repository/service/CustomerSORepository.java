package tomato_th.project_tomato.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tomato_th.project_tomato.model.service.CustomerServiceOrder;

import java.util.Date;
import java.util.List;

public interface CustomerSORepository extends JpaRepository<CustomerServiceOrder,Integer> {

    @Query("SELECT c FROM CustomerServiceOrder c WHERE c.customerService.customer_service_id=?1 ORDER BY c.orders.status ASC")
    List<CustomerServiceOrder> listByCustSId(int custServiceId);

    @Query("SELECT c FROM CustomerServiceOrder c WHERE c.customerService.customer_service_id=?1 AND c.created=?2")
    List<CustomerServiceOrder> geyCustomerSO(int custServiceId, Date date);

    @Query("SELECT c FROM CustomerServiceOrder c WHERE c.orders.status=?1 AND c.customerService.services.service_id=?2")
    Page<CustomerServiceOrder> listCustSOrderStatus(int status,int service_id, Pageable pageable);

    @Query("SELECT c FROM CustomerServiceOrder c WHERE c.customerService.services.service_id=?1")
    List<CustomerServiceOrder> getListCSOByServiceId(int serviceId);

    @Query("SELECT c FROM CustomerServiceOrder c WHERE  c.received_date=?1")
    List<CustomerServiceOrder> getCustOdReceivedDate(Date date);

    @Query("SELECT  c FROM CustomerServiceOrder c WHERE c.customerService.customer_service_id=?1 AND c.received_date=?2")
    List<CustomerServiceOrder> getCSOByCSIdAndReceivedDate(int customer_service_id,Date date);

}
