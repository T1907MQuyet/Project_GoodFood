package tomato_th.project_tomato.model.dto;

import javax.persistence.*;

@Entity
@Table(name = "product_menu_detail")
public class Product_menu_detailDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_menu_detail_id")
    private int product_menu_detail_id;

    @Column(name = "product_id")
    private int product_id;

    @Column(name = "menu_detail_id")
    private int menu_detail_id;


    public int getProduct_menu_detail_id() {
        return product_menu_detail_id;
    }

    public void setProduct_menu_detail_id(int product_menu_detail_id) {
        this.product_menu_detail_id = product_menu_detail_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getMenu_detail_id() {
        return menu_detail_id;
    }

    public void setMenu_detail_id(int menu_detail_id) {
        this.menu_detail_id = menu_detail_id;
    }
}
