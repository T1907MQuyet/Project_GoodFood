package tomato_th.project_tomato.repository.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tomato_th.project_tomato.model.service.CustomerServiceProduct;

import java.util.List;

public interface CustomerSPRepository extends JpaRepository<CustomerServiceProduct,Integer> {
    @Query("SELECT c FROM CustomerServiceProduct c WHERE c.customerService.customer_service_id = ?1 AND c.status=1")
    List<CustomerServiceProduct> getCustSPByCustomerService(int customer_service_id);

    @Query("SELECT c FROM CustomerServiceProduct c WHERE c.customerService.customer_service_id =?1 AND c.status=?2")
    List<CustomerServiceProduct> getCustSPByCustSStatus(int customer_service_id,int status);

    @Query("SELECT  c FROM  CustomerServiceProduct  c WHERE c.customerService.customer_service_id=?1")
    List<CustomerServiceProduct> getCustomerServiceProductByCSPId(int customer_service_id);
}
