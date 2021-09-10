package tomato_th.project_tomato.repository.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tomato_th.project_tomato.model.core.Order_detail;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<Order_detail,Integer> {
    @Query("SELECT c FROM Order_detail c WHERE  order_id = ?1")
    List<Order_detail> getByOrderId(int orderId);
}
