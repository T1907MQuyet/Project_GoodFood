package tomato_th.project_tomato.service.Impl.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tomato_th.project_tomato.model.core.Orders;
import tomato_th.project_tomato.repository.core.OrderRepository;
import tomato_th.project_tomato.service.OrderService;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public List<Orders> getAllOrders() {
        try{
            List<Orders> list = orderRepository.findAll();
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Orders getOrderById(int id) {
        try{
            Orders orders = orderRepository.findById(id).get();
            return orders;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateOrderStatus(int id,int status) {
        try{
            Orders orders = orderRepository.findById(id).get();
            orders.setUpdated(new Date());
            if (status==4)
            {
                orders.setPayment_status(1);
            }
            orders.setStatus(status);
            orderRepository.save(orders);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Orders saveOrder(Orders orders) {
        try{
            orders.setStatus(1);
            orders.setUpdated(new Date());
            orders.setCreated(new Date());
            Orders orderNew = orderRepository.save(orders);
            return orderNew;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean canceledOrder(int order_id,int status) {
        try{
            if(status==1)
            {
                Orders orderUpdate = orderRepository.findById(order_id).get();
                orderUpdate.setStatus(5);
                orderRepository.save(orderUpdate);
                return true;
            }
            return false;

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int countOrderByStatus(int status) {
        List<Orders> list = orderRepository.findAllByStatus(status);
        int count = list.size();
        return count;
    }

    @Override
    public List<Orders> listOrderMonth(int month) {
        try{
            List<Orders> listMonth = orderRepository.getOrderComplete(month);
            if (listMonth.size()<=0)
            {
                return null;
            }
            return listMonth;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public double totalByMonth(String startDate, String endDate) {
        double b = 0;
        try{
            b = orderRepository.getOrder1Complete(new SimpleDateFormat("yyyy-MM-dd").parse(startDate),new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
            return b;
        }catch (Exception e) {
            //e.printStackTrace();
        }
        return b;
    }

    @Override
    public double totalByDay(String date) {
        double b = 0;
        try{
            b = orderRepository.getRevenueToMonth(new SimpleDateFormat("yyyy-MM-dd").parse(date));
            return b;
        }catch (Exception e)
        {
            //e.printStackTrace();
        }
        return b;
    }

    @Override
    public List<Orders> listOrderByEmail(String email) {
        try{
            List<Orders> list = orderRepository.findByOrderEmail(email);
            return list;
        }catch (Exception e)
        {e.printStackTrace();}
        return null;
    }

    @Override
    public List<Orders> listOrderByStatus(int status) {
        try{
            List<Orders> list = orderRepository.getListByStatus(status);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<Orders> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.orderRepository.findPaginateOrder(pageable);
    }

    @Override
    public Page<Orders> findPagiCustomer(int pageNo, int pageSize, int cust_id) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.orderRepository.getByCustomerIdPaginate(cust_id,pageable);
    }

    @Override
    public Page<Orders> findPagiWatting(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.orderRepository.findPagiOrderWatting(pageable);
    }

    @Override
    public Page<Orders> findPagiConfirmed(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.orderRepository.findPagiOrderConfirmed(pageable);
    }

    @Override
    public Page<Orders> findPagiShipping(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.orderRepository.findPagiOrderShipping(pageable);
    }

    @Override
    public Page<Orders> findPagiComplete(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.orderRepository.findPagiOrderComplete(pageable);
    }

    @Override
    public Page<Orders> findPagiCancelled(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.orderRepository.findPagiOrderCancelled(pageable);
    }

    @Override
    public Page<Orders> findPaginatedOdStatusService(int status, int service_id, int pageNo, int pageSize) {
        try{
            Pageable pageable = PageRequest.of(pageNo-1,pageSize);
            Page<Orders> list = orderRepository.getOrdersByStatusAndService(status,service_id,pageable);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<Orders> findPaginatedOdAllStatusService(int service_id, int pageNo, int pageSize) {
        try{
            Pageable pageable = PageRequest.of(pageNo-1,pageSize);
            Page<Orders> list = orderRepository.getOrdersAllStatusAndService(service_id,pageable);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<Orders> findPaginatedOdByStatusCustomer(int customerId, int status, int pageNo, int pageSize) {
        try{
            Pageable pageable = PageRequest.of(pageNo-1,pageSize);
            Page<Orders> list = orderRepository.getByCustomerStatusPaginate(customerId,status,pageable);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<Orders> findPaginatedOdByCustomerAndService(int service_id, int customer_id, int pageNo, int pageSize) {
        try{
            Pageable pageable = PageRequest.of(pageNo-1,pageSize);
            Page<Orders> list = orderRepository.getOrderByCustomerAndService(service_id,customer_id,pageable);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
