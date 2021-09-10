package tomato_th.project_tomato.model.core;

import javax.persistence.*;

@Entity
public class Product_menu_detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_menu_detail_id;

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "menu_detail_id",referencedColumnName = "menu_detail_id")
    private Menu_detail menu_detail;

    public Product_menu_detail() {
    }

    public Product_menu_detail(Product product, Menu_detail menu_detail) {
        this.product = product;
        this.menu_detail = menu_detail;
    }

    public int getProduct_menu_detail_id() {
        return product_menu_detail_id;
    }

    public void setProduct_menu_detail_id(int product_menu_detail_id) {
        this.product_menu_detail_id = product_menu_detail_id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Menu_detail getMenu_detail() {
        return menu_detail;
    }

    public void setMenu_detail(Menu_detail menu_detail) {
        this.menu_detail = menu_detail;
    }
}
