package tomato_th.project_tomato.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
public class Banners {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int banner_id;
    @NotEmpty(message = "type banner name must not be empty")
    private String banner_name;
    private String image_links;
    private int status;
    private Date created;
    private Date updated;

    @ManyToOne
    @JoinColumn(name = "type_banner_id",referencedColumnName = "type_banner_id")
    private Type_Banner type_banner;

    public Banners() {
    }

    public Banners(int banner_id, String banner_name, String image_links, int status, Date created, Date updated, Type_Banner type_banner) {
        this.banner_id = banner_id;
        this.banner_name = banner_name;
        this.image_links = image_links;
        this.status = status;
        this.created = created;
        this.updated = updated;
        this.type_banner = type_banner;
    }

    public int getBanner_id() {
        return banner_id;
    }

    public void setBanner_id(int banner_id) {
        this.banner_id = banner_id;
    }

    public String getBanner_name() {
        return banner_name;
    }

    public void setBanner_name(String banner_name) {
        this.banner_name = banner_name;
    }

    public String getImage_links() {
        return image_links;
    }

    public void setImage_links(String image_links) {
        this.image_links = image_links;
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

    public Type_Banner getType_banner() {
        return type_banner;
    }

    public void setType_banner(Type_Banner type_banner) {
        this.type_banner = type_banner;
    }
}
