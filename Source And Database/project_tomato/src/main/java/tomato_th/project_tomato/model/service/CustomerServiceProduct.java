package tomato_th.project_tomato.model.service;

import tomato_th.project_tomato.model.core.Product;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CustomerServiceProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    private int status;
    private Date created;
    private Date updated;

    @ManyToOne
    @JoinColumn(name = "customer_service_id",referencedColumnName = "customer_service_id")
    private CustomerService customerService;

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "product_id")
    private Product product;

    public CustomerServiceProduct() {
    }

    public CustomerServiceProduct(int id, int quantity, int status, Date created, Date updated, CustomerService customerService, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.status = status;
        this.created = created;
        this.updated = updated;
        this.customerService = customerService;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
