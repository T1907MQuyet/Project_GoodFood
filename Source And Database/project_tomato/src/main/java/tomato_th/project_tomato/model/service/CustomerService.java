package tomato_th.project_tomato.model.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import tomato_th.project_tomato.model.Customer;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;

@Entity
public class CustomerService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customer_service_id;
    private String note;

    private int received_date;
    @NotEmpty(message = "Received address  must not empty")
    private String received_address;
    @NotEmpty(message = "Received phone number  must not empty")
    private String received_phone_number;
    private int status;
    @Temporal(TemporalType.DATE)
    private Date created;
    @Temporal(TemporalType.DATE)
    private Date updated;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "service_id",referencedColumnName = "service_id")
    private Services services;

    @OneToMany(mappedBy = "customerService")
    private Set<CustomerServiceProduct> customerServiceProducts;
    @OneToMany(mappedBy = "customerService")
    private Set<CustomerServiceOrder> customerServiceOrders;

    public CustomerService() {
    }

    public CustomerService(int customer_service_id) {
        this.customer_service_id = customer_service_id;
    }

    public CustomerService(int customer_service_id, String note, int received_date, int status, Date created, Date updated, Customer customer, Services services) {
        this.customer_service_id = customer_service_id;
        this.note = note;
        this.received_date = received_date;
        this.status = status;
        this.created = created;
        this.updated = updated;
        this.customer = customer;
        this.services = services;
    }

    public int getCustomer_service_id() {
        return customer_service_id;
    }

    public void setCustomer_service_id(int customer_service_id) {
        this.customer_service_id = customer_service_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getReceived_date() {
        return received_date;
    }

    public void setReceived_date(int received_date) {
        this.received_date = received_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public Set<CustomerServiceProduct> getCustomerServiceProducts() {
        return customerServiceProducts;
    }

    public void setCustomerServiceProducts(Set<CustomerServiceProduct> customerServiceProducts) {
        this.customerServiceProducts = customerServiceProducts;
    }

    public Set<CustomerServiceOrder> getCustomerServiceOrders() {
        return customerServiceOrders;
    }

    public void setCustomerServiceOrders(Set<CustomerServiceOrder> customerServiceOrders) {
        this.customerServiceOrders = customerServiceOrders;
    }

    public String getReceived_address() {
        return received_address;
    }

    public void setReceived_address(String received_address) {
        this.received_address = received_address;
    }

    public String getReceived_phone_number() {
        return received_phone_number;
    }

    public void setReceived_phone_number(String received_phone_number) {
        this.received_phone_number = received_phone_number;
    }
}
