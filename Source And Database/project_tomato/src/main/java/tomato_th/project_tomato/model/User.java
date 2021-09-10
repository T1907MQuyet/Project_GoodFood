package tomato_th.project_tomato.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "user",uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "FullName must not empty")
//    @Size(min = 5,max = 30)
    private String username;
    @NotEmpty(message = "Password must not empty")
//    @Size(min = 6,message = "password with 6 or more letters")
    private String password;
    @Email
    @NotEmpty(message = "Email must not empty")
    private String email;

    @OneToMany(mappedBy = "userId")
    private List<User_roles> listUserRole;

    public User() {
    }

    public User(String username,String password,String email, List<User_roles> listUserRole) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.listUserRole = listUserRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<User_roles> getListUserRole() {
        return listUserRole;
    }

    public void setListUserRole(List<User_roles> listUserRole) {
        this.listUserRole = listUserRole;
    }
}
