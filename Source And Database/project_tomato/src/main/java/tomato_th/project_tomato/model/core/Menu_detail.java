package tomato_th.project_tomato.model.core;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
public class Menu_detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menu_detail_id;
    @NotEmpty(message = "Menu name  must not empty")
    @Size(min = 3,max = 30)
    private String menu_detail_name;
    private int status;
    private Date created;
    private Date updated;

    @ManyToOne
    @NotNull(message = "menu_id must not empty")
    @JoinColumn(name = "menu_id",referencedColumnName = "menu_id")
    private Menu menu;

    @OneToMany(mappedBy = "menu_detail")
    private Set<Product_menu_detail> product_menu_details()
    {
        return this.product_menu_details();
    }

    public Menu_detail() {
    }

    public Menu_detail(String menu_detail_name, int status, Date created, Date updated, Menu menu) {
        this.menu_detail_name = menu_detail_name;
        this.status = status;
        this.created = created;
        this.updated = updated;
        this.menu = menu;
    }

    public int getMenu_detail_id() {
        return menu_detail_id;
    }

    public void setMenu_detail_id(int menu_detail_id) {
        this.menu_detail_id = menu_detail_id;
    }

    public String getMenu_detail_name() {
        return menu_detail_name;
    }

    public void setMenu_detail_name(String menu_detail_name) {
        this.menu_detail_name = menu_detail_name;
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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
