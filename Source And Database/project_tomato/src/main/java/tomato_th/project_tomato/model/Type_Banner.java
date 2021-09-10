package tomato_th.project_tomato.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
public class Type_Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int type_banner_id;
    @NotEmpty(message = "type banner name must not be empty")
    @Size(min = 5,max = 30)
    @Column(unique = true)
    private String type_name;
    private int status;
    private Date created;
    private Date updated;

    @OneToMany(mappedBy = "type_banner")
    private Set<Banners> banners;

    public Type_Banner() {
    }

    public Type_Banner(int type_banner_id, String type_name, int status, Date created, Date updated, Set<Banners> banners) {
        this.type_banner_id = type_banner_id;
        this.type_name = type_name;
        this.status = status;
        this.created = created;
        this.updated = updated;
        this.banners = banners;
    }

    public int getType_banner_id() {
        return type_banner_id;
    }

    public void setType_banner_id(int type_banner_id) {
        this.type_banner_id = type_banner_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
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

    public Set<Banners> getBanners() {
        return banners;
    }

    public void setBanners(Set<Banners> banners) {
        this.banners = banners;
    }
}
