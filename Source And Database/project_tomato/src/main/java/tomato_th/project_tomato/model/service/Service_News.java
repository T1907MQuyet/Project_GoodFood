package tomato_th.project_tomato.model.service;


import tomato_th.project_tomato.model.News;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Service_News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int news_service_id;

    @ManyToOne
    @JoinColumn(name = "news_id",referencedColumnName = "news_id")
    private News news;

    @ManyToOne
    @JoinColumn(name = "service_id",referencedColumnName = "service_id")
    private Services services;
    private Date created;
    private Date updated;

    public Service_News() {
    }

    public Service_News(int news_service_id, News news, Services services, Date created, Date updated) {
        this.news_service_id = news_service_id;
        this.news = news;
        this.services = services;
        this.created = created;
        this.updated = updated;
    }

    public int getNews_service_id() {
        return news_service_id;
    }

    public void setNews_service_id(int news_service_id) {
        this.news_service_id = news_service_id;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
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
}
