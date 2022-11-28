package csed.swe.studentunity.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class User implements Serializable {
    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
    private String email;
    @Column(nullable = false)
    private String role;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, updatable = false)
    private int id;
    private String revisionNotificationToken;


    public User() {}

    public User(String email, String role, String password, String name, int id) {
        this.email = email;
        this.role = role;
        this.password = password;
        this.name = name;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRevisionNotificationToken() {
        return revisionNotificationToken;
    }

    public void setRevisionNotificationToken(String revisionNotificationToken) {
        this.revisionNotificationToken = revisionNotificationToken;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", revisionNotificationToken='" + revisionNotificationToken + '\'' +
                '}';
    }

}
