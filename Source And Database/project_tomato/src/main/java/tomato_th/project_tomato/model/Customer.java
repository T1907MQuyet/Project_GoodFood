package tomato_th.project_tomato.model;

import tomato_th.project_tomato.model.core.Orders;
import tomato_th.project_tomato.model.service.CustomerService;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "customer",uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customer_id;
    @NotEmpty(message = "full name must not be empty")
    private String username;
    @Email
    private String email;
    @NotEmpty(message = "password must not be empty")
    private String password;

    private String avatar;
    private String address;
    private String phonenumber;

    @OneToMany(mappedBy = "customer")
    private Set<Orders> orders()
    {
        return this.orders();
    }

    @OneToMany(mappedBy = "customer")
    private Set<CustomerService> customerServices;

    public Customer() {
    }

    public Customer(int customer_id) {
        this.customer_id = customer_id;
    }

    public Customer(@NotEmpty String username, @Email String email, @NotEmpty String password, String avatar, String address, String phonenumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.address = address;
        this.phonenumber = phonenumber;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

}
