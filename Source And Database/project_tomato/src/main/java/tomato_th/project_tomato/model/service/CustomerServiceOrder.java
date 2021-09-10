package tomato_th.project_tomato.model.service;


import tomato_th.project_tomato.model.core.Orders;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CustomerServiceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.DATE)
    private Date received_date;//ngay nhan don that
    @Temporal(TemporalType.DATE)
    private Date created;//ngay nhan don + before
    @Temporal(TemporalType.DATE)
    private Date created_date;
    @ManyToOne
    @JoinColumn(name = "customer_service_id",referencedColumnName = "customer_service_id")
    private CustomerService customerService;

    @OneToOne
    @JoinColumn(name = "orderId",referencedColumnName = "orderId")
    private Orders orders;

    public CustomerServiceOrder() {
    }

    public CustomerServiceOrder(int id, Date received_date, Date created, Date created_date, CustomerService customerService, Orders orders) {
        this.id = id;
        this.received_date = received_date;
        this.created = created;
        this.created_date = created_date;
        this.customerService = customerService;
        this.orders = orders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Date getReceived_date() {
        return received_date;
    }

    public void setReceived_date(Date received_date) {
        this.received_date = received_date;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

}
