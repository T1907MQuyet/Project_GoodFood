package tomato_th.project_tomato.model.core;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Order_detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_detail_id;
    @NotNull(message = "quantity must not be null")
    private int quantity;
    @NotNull(message = "price must not be null")
    private double price;

    @ManyToOne
    @NotNull(message = "product id must not be null")
    @JoinColumn(name = "product_id",referencedColumnName = "product_id")
    private Product product;

    @ManyToOne
    @NotNull(message = "order id must not be null")
    @JoinColumn(name = "order_id",referencedColumnName = "orderId")
    private Orders orders;

    public Order_detail() {
    }

    public Order_detail(int quantity, double price, Product product, Orders orders) {
        this.quantity = quantity;
        this.price = price;
        this.product = product;
        this.orders = orders;
    }

    public int getOrder_detail_id() {
        return order_detail_id;
    }

    public void setOrder_detail_id(int order_detail_id) {
        this.order_detail_id = order_detail_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Orders getOrder() {
        return orders;
    }

    public void setOrder(Orders order) {
        this.orders = order;
    }
}
