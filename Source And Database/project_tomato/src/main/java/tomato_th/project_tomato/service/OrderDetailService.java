package tomato_th.project_tomato.service;

import tomato_th.project_tomato.model.core.Order_detail;

import java.util.List;

public interface OrderDetailService {
    List<Order_detail> getAllByOrderId(int orderId);
    boolean saveOrderDetail(Order_detail order_detail);

}
