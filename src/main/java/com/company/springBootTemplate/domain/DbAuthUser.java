package com.company.springBootTemplate.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

@Entity
@Table(name = "db_auth_user")
public class DbAuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "is_active")
    private Boolean isActive = false;
    @Column(name = "roles")
    private String roles = "";


    public DbAuthUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public List<String> getRoleList() {
        if (!roles.isEmpty()) {
            return Arrays.asList(roles.split(","));
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DbAuthUser.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("userName='" + userName + "'")
                .add("password='" + password + "'")
                .add("isActive=" + isActive)
                .add("roles='" + roles + "'")
                .toString();
    }
}
