package tomato_th.project_tomato.model;


import tomato_th.project_tomato.model.service.Service_News;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;


@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int news_id;

    @NotEmpty(message = "title name must not be empty")
    @Column(columnDefinition="TEXT")
    private String title_name;
    @NotEmpty(message = "title content must not be empty")
    @Column(columnDefinition="TEXT")
    private String title_content;
    @NotEmpty(message = "content must not be empty")
    @Column(columnDefinition="TEXT")
    private String content;

    private String imageLink;
    private Date created;
    private Date updated;
    private int status;
    private int home_active;
    @ManyToOne
    @JoinColumn(name = "type_new_id",referencedColumnName = "type_new_id")
    private TypeNews type_news;

    @OneToMany(mappedBy = "news")
    private Set<Service_News> service_news;


    public News() {
    }

    public News(String title_name, String title_content, String content, String imageLink, Date created, Date updated, int status, int home_active, TypeNews type_news) {
        this.title_name = title_name;
        this.title_content = title_content;
        this.content = content;
        this.imageLink = imageLink;
        this.created = created;
        this.updated = updated;
        this.status = status;
        this.home_active = home_active;
        this.type_news = type_news;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public String getTitle_name() {
        return title_name;
    }

    public void setTitle_name(String title_name) {
        this.title_name = title_name;
    }

    public String getTitle_content() {
        return title_content;
    }

    public void setTitle_content(String title_content) {
        this.title_content = title_content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
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

    public int getHome_active() {
        return home_active;
    }

    public void setHome_active(int home_active) {
        this.home_active = home_active;
    }

    public TypeNews getType_news() {
        return type_news;
    }

    public void setType_news(TypeNews type_news) {
        this.type_news = type_news;
    }

    public Set<Service_News> getService_news() {
        return service_news;
    }

    public void setService_news(Set<Service_News> service_news) {
        this.service_news = service_news;
    }
}
