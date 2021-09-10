package tomato_th.project_tomato.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
public class Feedbacks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @NotEmpty(message = "fullname must not be empty")
    private String fullname;


    @NotEmpty(message = "email must not be empty")
    private String email;

    private String phonenumber;

    @NotEmpty(message = "content must not be empty")
    @Lob
    private String content;

    private int status;

    private Date created;

    public Feedbacks() {
    }

    public Feedbacks(String fullname, String email, String phonenumber, String content, int status, Date created) {
        this.fullname = fullname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.content = content;
        this.status = status;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
