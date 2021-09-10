package tomato_th.project_tomato.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tomato_th.project_tomato.model.service.CustomerService;

import java.util.List;

public interface CustomerServiceRepository extends JpaRepository<CustomerService,Integer> {

    @Query("SELECT c FROM CustomerService c WHERE c.customer.customer_id=?1 ")
    Page<CustomerService> getCSPaginated(Pageable pageable,int cust_id);

    @Query("SELECT c FROM CustomerService c WHERE c.status=1 OR c.status=2 OR c.status=0 ORDER BY c.status, c.updated ")
    Page<CustomerService> getCSStatusPaginated(Pageable pageable);

    @Query("SELECT c FROM CustomerService c WHERE c.status=1")
    List<CustomerService> getAllCSByShow();

    //serach by email,service_id and status
    @Query("SELECT c FROM CustomerService c WHERE c.customer.email LIKE %:email% AND c.services.service_id=:service_id AND c.status=:status")
    List<CustomerService> getListByEmailServiceAndStatus(@Param("email")String email, int service_id, int status);

    @Query("SELECT c FROM CustomerService c WHERE c.status=?1")
    Page<CustomerService> getByStatusPaginated(int status,Pageable pageable);

    @Query("SELECT c FROM CustomerService c WHERE c.services.service_id=?1 AND c.status=?2 AND c.received_date=?3")
    Page<CustomerService> getByServiceAndReceivedDate(int service_id,int status,int received_date,Pageable pageable);

    @Query("SELECT c FROM CustomerService c WHERE c.services.service_id=?1 AND c.status=?2")
    Page<CustomerService> getByServiceAll(int service_id,int status,Pageable pageable);

    @Query("SELECT c FROM CustomerService c WHERE c.status=?1")
    List<CustomerService> getListByStatus(int status);

    @Query("SELECT c FROM CustomerService c WHERE c.services.service_id=?1 AND c.status=?2")
    List<CustomerService> getListByServiceAndReceivedDate(int service_id,int status);
}
