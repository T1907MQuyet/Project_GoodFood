package tomato_th.project_tomato.service;

import org.springframework.data.domain.Page;
import tomato_th.project_tomato.model.core.Orders;


import java.util.List;

public interface OrderService {
    List<Orders> getAllOrders();
    Orders getOrderById(int id);
    boolean updateOrderStatus(int id, int status);
    Orders saveOrder(Orders orders);
    boolean canceledOrder(int order_id, int status);
    int countOrderByStatus(int status);

    List<Orders> listOrderMonth(int month);

    double totalByMonth(String startDate, String endDate);
    double totalByDay(String date);
    List<Orders> listOrderByEmail(String email);
    List<Orders> listOrderByStatus(int status);

    Page<Orders> findPaginated(int pageNo, int pageSize);

    Page<Orders> findPagiCustomer(int pageNo, int pageSize, int cust_id);

    Page<Orders> findPagiWatting(int pageNo, int pageSize);
    Page<Orders> findPagiConfirmed(int pageNo, int pageSize);
    Page<Orders> findPagiShipping(int pageNo, int pageSize);
    Page<Orders> findPagiComplete(int pageNo, int pageSize);
    Page<Orders> findPagiCancelled(int pageNo, int pageSize);

    Page<Orders> findPaginatedOdStatusService(int status, int service_id, int pageNo, int pageSize);
    Page<Orders> findPaginatedOdAllStatusService(int service_id, int pageNo, int pageSize);
    Page<Orders> findPaginatedOdByStatusCustomer(int customerId,int status,int pageNo,int pageSize);
    Page<Orders> findPaginatedOdByCustomerAndService(int service_id,int customer_id,int pageNo,int pageSize);

}
