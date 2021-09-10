package tomato_th.project_tomato.model.core;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menu_id;
    @NotEmpty(message = "Menu name  must not empty")
    @Size(min = 3,max = 30)
    private String menu_name;
    private Date created;
    private Date updated;
    @Min(value = 1,message = "please chose a status")
    private int status;

    @OneToMany(mappedBy = "menu")
    private Set<Menu_detail> menu_details()
    {
        return this.menu_details();
    }

    public Menu() {
    }

    public Menu(String menu_name, Date created, Date updated, int status) {
        this.menu_name = menu_name;
        this.created = created;
        this.updated = updated;
        this.status = status;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
