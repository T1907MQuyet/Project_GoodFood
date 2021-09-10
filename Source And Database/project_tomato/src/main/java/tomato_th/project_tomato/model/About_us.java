package tomato_th.project_tomato.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class About_us {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int news_id;

    @NotEmpty(message = "Please fill in News Name!")
    @Size(min = 3,max = 30,message = "News name size must be between 3 and 30 letters.")
    private String title;

    @NotEmpty(message = "Please fill in Description!")
    private String description;


    @NotEmpty(message = "Please fill in Content!")
    private String content;


    private String image;

    private int status;
    private Date created;
    private Date updated;

    public About_us() {
    }


    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
