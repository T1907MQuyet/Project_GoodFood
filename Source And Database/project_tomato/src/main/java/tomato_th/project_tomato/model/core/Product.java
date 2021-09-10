package tomato_th.project_tomato.model.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import tomato_th.project_tomato.model.service.CustomerServiceProduct;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;
    @NotEmpty(message = "Product name  must not empty")
    @Size(min = 3,max = 30)
    private String product_name;

    private double price;
    @Min(0)
    private int discount;
    @NotEmpty(message = "Description  must not empty")
    @Lob
    private String descriptions;
    private int status;
    @Min(0)
    private int priority;

    private String image;
    private Date created;
    private Date updated;
    @ManyToOne
    @JoinColumn(name = "cate_detail_id",referencedColumnName = "cate_detail_id")
    @JsonIgnoreProperties("category_detail")
    private Category_detail category_detail;

    @OneToMany(mappedBy = "product")
    private Set<Product_menu_detail> product_menu_details;

    @OneToMany(mappedBy = "product")
    private Set<CustomerServiceProduct> customerServiceProducts;

    @OneToMany(mappedBy = "product")
    private Set<Order_detail> order_details()
    {
        return this.order_details();
    }

    public Product() {
    }

    public Product(int product_id) {
        this.product_id = product_id;
    }

    public Product(String product_name, double price, int discount, String descriptions, int status, int priority, String image, Category_detail category_detail, Set<Product_menu_detail> product_menu_details) {
        this.product_name = product_name;
        this.price = price;
        this.discount = discount;
        this.descriptions = descriptions;
        this.status = status;
        this.priority = priority;
        this.image = image;
        this.category_detail = category_detail;
        this.product_menu_details = product_menu_details;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category_detail getCategory_detail() {
        return category_detail;
    }

    public void setCategory_detail(Category_detail category_detail) {
        this.category_detail = category_detail;
    }

    public Set<Product_menu_detail> getProduct_menu_details() {
        return product_menu_details;
    }

    public void setProduct_menu_details(Set<Product_menu_detail> product_menu_details) {
        this.product_menu_details = product_menu_details;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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

    public Set<CustomerServiceProduct> getCustomerServiceProducts() {
        return customerServiceProducts;
    }

    public void setCustomerServiceProducts(Set<CustomerServiceProduct> customerServiceProducts) {
        this.customerServiceProducts = customerServiceProducts;
    }
}
