package tomato_th.project_tomato.model;


import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class User_roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    private Role roleId;

    public User_roles() {
    }

    public User_roles(User userId, Role roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }
}
