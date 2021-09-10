package tomato_th.project_tomato.model.service;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int service_id;
    @NotEmpty(message = "Product name  must not empty")
    @Size(min = 3,max = 30)
    private String service_name;
    @Lob
    private String description;
    private int status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date created;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updated;

    @OneToMany(mappedBy = "services")
    private Set<Calender> calenders;
    @OneToMany(mappedBy = "services")
    private List<CustomerService> customerServices;

    @OneToMany(mappedBy = "services")
    private Set<Service_News> service_news;

    public Services() {
    }

    public Services(int service_id, String service_name, int status, Date created, Date updated, Set<Calender> calenders, List<CustomerService> customerServices) {
        this.service_id = service_id;
        this.service_name = service_name;
        this.status = status;
        this.created = created;
        this.updated = updated;
        this.calenders = calenders;
        this.customerServices = customerServices;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
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

    public Set<Calender> getCalenders() {
        return calenders;
    }

    public void setCalenders(Set<Calender> calenders) {
        this.calenders = calenders;
    }

    public List<CustomerService> getCustomerServices() {
        return customerServices;
    }

    public void setCustomerServices(List<CustomerService> customerServices) {
        this.customerServices = customerServices;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Service_News> getService_news() {
        return service_news;
    }

    public void setService_news(Set<Service_News> service_news) {
        this.service_news = service_news;
    }
}
