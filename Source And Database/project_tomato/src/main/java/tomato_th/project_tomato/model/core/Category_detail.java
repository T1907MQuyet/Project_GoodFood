package tomato_th.project_tomato.model.core;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
public class Category_detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cate_detail_id;
    @NotEmpty(message = "Category name must not empty")
    @Size(min = 3,max = 30,message = "Category name size must be between 3 and 30")
    private String cate_detail_name;
    @Min(value = 1,message = "please chose a status")
    private int status;
    private Date created;
    private Date updated;


    @ManyToOne
    @JoinColumn(name = "cate_id",referencedColumnName = "cate_id")
    @NotNull(message = "Category_id must not empty")
    @JsonIgnoreProperties("category")
    private Category category;

    @OneToMany(mappedBy = "category_detail")
    private Set<Product> products()
    {
        return this.products();
    }

    public Category_detail() {
    }

    public Category_detail(String cate_detail_name, int status, Date created, Date updated, Category category) {
        this.cate_detail_name = cate_detail_name;
        this.status = status;
        this.created = created;
        this.updated = updated;
        this.category = category;
    }

    public int getCate_detail_id() {
        return cate_detail_id;
    }

    public void setCate_detail_id(int cate_detail_id) {
        this.cate_detail_id = cate_detail_id;
    }

    public String getCate_detail_name() {
        return cate_detail_name;
    }

    public void setCate_detail_name(String cate_detail_name) {
        this.cate_detail_name = cate_detail_name;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
