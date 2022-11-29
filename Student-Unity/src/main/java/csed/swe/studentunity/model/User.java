package csed.swe.studentunity.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable {

    private Long id;
    private String name;
    @Id
    @Column(nullable = false, updatable = false)
    private String email;
    private String password;
    private String role;
    private String token;

    public User() {}

    public User(String name, String email, String password, String role, String token) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + role + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}