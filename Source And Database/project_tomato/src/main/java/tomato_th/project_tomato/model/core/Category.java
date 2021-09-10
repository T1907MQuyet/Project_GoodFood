package tomato_th.project_tomato.model.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cate_id;
    @NotEmpty(message = "category name must not be empty")
    @Size(min = 5,max = 30)
    private String cate_name;
    @Min(value = 1,message = "please chose a status")
    private int status;
    private Date created;

    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties("category")
    private Set<Category_detail> category_details(){
        return this.category_details();
    }

    public Category() {
    }

    public Category(String cate_name, int status, Date created) {
        this.cate_name = cate_name;
        this.status = status;
        this.created = created;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
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
