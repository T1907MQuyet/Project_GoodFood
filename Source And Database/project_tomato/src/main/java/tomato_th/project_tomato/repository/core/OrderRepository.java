package tomato_th.project_tomato.repository.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tomato_th.project_tomato.model.core.Orders;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Integer> {
    @Query("SELECT c FROM Orders c WHERE  c.customer.customer_id = ?1")
    List<Orders> getByCustomerId(int customerId);

    @Query("SELECT c FROM Orders c WHERE  c.customer.customer_id = ?1 ORDER BY c.status ASC")
    Page<Orders> getByCustomerIdPaginate(int customerId, Pageable pageable);

    @Query("SELECT o FROM Orders o WHERE o.customer.customer_id=?1 AND o.status=?2  ")
    Page<Orders> getByCustomerStatusPaginate(int customerId,int status,Pageable pageable);

    List<Orders> findAllByStatus(int status);

    @Query("SELECT o FROM Orders o ORDER BY o.status ASC ")
    Page<Orders> findPaginateOrder(Pageable pageable);

    @Query("SELECT o FROM Orders o WHERE o.status = 1 ORDER BY o.created ASC ")
    Page<Orders> findPagiOrderWatting(Pageable pageable);

    @Query("SELECT o FROM Orders o WHERE o.status = 2 ORDER BY o.updated ASC ")
    Page<Orders> findPagiOrderConfirmed(Pageable pageable);

    @Query("SELECT o FROM Orders o WHERE o.status = 3 ORDER BY o.updated ASC ")
    Page<Orders> findPagiOrderShipping(Pageable pageable);

    @Query("SELECT o FROM Orders o WHERE o.status = 4 ORDER BY o.updated ASC ")
    Page<Orders> findPagiOrderComplete(Pageable pageable);

    @Query("SELECT o FROM Orders o WHERE o.status = 5 ORDER BY o.updated ASC ")
    Page<Orders> findPagiOrderCancelled(Pageable pageable);

    @Query(value = "from Orders WHERE month(created)= ?1",nativeQuery = true)
    List<Orders> getOrderComplete(int month);

    @Query(value = "SELECT SUM(o.total_price) from Orders o where o.status = 4 AND o.created BETWEEN :startDate AND :endDate")
    double getOrder1Complete(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT SUM(o.total_price)FROM Orders o WHERE o.status=4 AND o.updated=?1")
    double getRevenueToMonth(Date date);

    @Query("SELECT o FROM Orders o WHERE o.order_email LIKE %:email%")
    List<Orders> findByOrderEmail(@Param("email")String email);

    @Query("SELECT o FROM Orders o WHERE o.status = ?1")
    List<Orders> getListByStatus(int status);

    @Query("SELECT o FROM Orders o WHERE o.status =?1 AND  o.customerServiceOrders.customerService.services.service_id=?2")
    Page<Orders> getOrdersByStatusAndService(int status,int service_id,Pageable pageable);

    @Query("SELECT o FROM Orders o WHERE o.customerServiceOrders.customerService.services.service_id=?1 ORDER BY o.status ASC")
    Page<Orders> getOrdersAllStatusAndService(int service_id,Pageable pageable);

    @Query("SELECT o FROM Orders o WHERE o.customerServiceOrders.customerService.services.service_id=?1 AND o.customer.customer_id=?2 ORDER BY o.status ASC")
    Page<Orders> getOrderByCustomerAndService(int service_id,int customer_id,Pageable pageable);
}
