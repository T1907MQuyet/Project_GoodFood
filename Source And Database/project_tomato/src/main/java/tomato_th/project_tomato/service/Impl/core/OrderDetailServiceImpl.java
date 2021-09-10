package tomato_th.project_tomato.service.Impl.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tomato_th.project_tomato.model.core.Order_detail;
import tomato_th.project_tomato.repository.core.OrderDetailRepository;
import tomato_th.project_tomato.service.OrderDetailService;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<Order_detail> getAllByOrderId(int orderId) {
        try{
            List<Order_detail> list = orderDetailRepository.getByOrderId(orderId);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveOrderDetail(Order_detail order_detail) {
        try{
            orderDetailRepository.save(order_detail);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
