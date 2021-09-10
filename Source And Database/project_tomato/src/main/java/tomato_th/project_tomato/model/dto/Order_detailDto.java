package tomato_th.project_tomato.model.dto;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order_detail")
public class Order_detailDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_detail_id;
    @NotNull(message = "quantity must not be null")
    private int quantity;
    @NotNull(message = "price must not be null")
    private double price;
    @NotNull(message = "product id must not be null")
    private int product_id;

    @NotNull(message = "order id must not be null")
    private int order_id;

    public Order_detailDto() {
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

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
}
