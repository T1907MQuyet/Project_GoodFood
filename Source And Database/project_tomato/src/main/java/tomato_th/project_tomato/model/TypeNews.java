package tomato_th.project_tomato.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;

@Entity
public class TypeNews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int type_new_id;

    @NotEmpty(message = "type new name must not be empty")
    private String type_name;
    private int status;
    private Date created;

    @OneToMany(mappedBy = "type_news")
    private Set<News> news(){
        return this.news();
    }
    public TypeNews() {
    }

    public TypeNews(String type_name, int status, Date created) {
        this.type_name = type_name;
        this.status = status;
        this.created = created;
    }

    public int getType_new_id() {
        return type_new_id;
    }

    public void setType_new_id(int type_new_id) {
        this.type_new_id = type_new_id;
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
}
