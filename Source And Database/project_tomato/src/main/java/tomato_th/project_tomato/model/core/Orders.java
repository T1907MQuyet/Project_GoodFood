package tomato_th.project_tomato.model.core;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.joda.time.DateTime;
import tomato_th.project_tomato.model.Customer;
import tomato_th.project_tomato.model.service.CustomerServiceOrder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @NotEmpty(message = "full name  must not empty")
    private String fullname;
    @NotEmpty(message = "Email  must not empty")
    private String order_email;
    @NotEmpty(message = "address must not empty")
    private String order_address;

    @NotEmpty(message = "phone number must not empty")
    private String phone_number;
    @Column(columnDefinition="TEXT")
    private String order_note;
    private int status;
    @NotEmpty(message = "Payment  must not empty")
    private String payment;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "received_date")
    @Temporal(TemporalType.DATE)
    private Date received_date;
    private double total_price;

    private int payment_status = 0;
    @Temporal(TemporalType.DATE)
    private Date created;
    @Temporal(TemporalType.DATE)
    private Date updated;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "orders")
    private Set<Order_detail> order_details()
    {
        return this.order_details();
    }

    @OneToOne(mappedBy = "orders")
    private CustomerServiceOrder customerServiceOrders;

    public Orders() {
    }



    public Orders(@NotEmpty(message = "full name  must not empty") String fullname, @NotEmpty(message = "Email  must not empty") String order_email, @NotEmpty(message = "address must not empty") String order_address, @NotEmpty(message = "phone number must not empty") String phone_number, String order_note, int status, @NotEmpty(message = "Payment  must not empty") String payment, double total_price, int payment_status, Date created, Date updated, Customer customer) {
        this.fullname = fullname;
        this.order_email = order_email;
        this.order_address = order_address;
        this.phone_number = phone_number;
        this.order_note = order_note;
        this.status = status;
        this.payment = payment;
        this.total_price = total_price;
        this.payment_status = payment_status;
        this.created = created;
        this.updated = updated;
        this.customer = customer;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getOrder_email() {
        return order_email;
    }

    public void setOrder_email(String order_email) {
        this.order_email = order_email;
    }

    public String getOrder_address() {
        return order_address;
    }

    public void setOrder_address(String order_address) {
        this.order_address = order_address;
    }

    public String getOrder_note() {
        return order_note;
    }

    public void setOrder_note(String order_note) {
        this.order_note = order_note;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(int payment_status) {
        this.payment_status = payment_status;
    }

    public Date getReceived_date() {
        return received_date;
    }

    public void setReceived_date(Date received_date) {
        this.received_date = received_date;
    }

    public CustomerServiceOrder getCustomerServiceOrders() {
        return customerServiceOrders;
    }

    public void setCustomerServiceOrders(CustomerServiceOrder customerServiceOrders) {
        this.customerServiceOrders = customerServiceOrders;
    }
}
