package tomato_th.project_tomato.repository.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tomato_th.project_tomato.model.dto.Order_detailDto;

import java.util.List;

public interface OrderDetailDtoRepository extends JpaRepository<Order_detailDto,Integer> {
    @Query("SELECT c FROM Order_detailDto c WHERE  order_id = ?1")
    List<Order_detailDto> getByOrderId(int orderId);
}
