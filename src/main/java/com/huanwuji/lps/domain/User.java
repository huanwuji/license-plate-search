package com.huanwuji.lps.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Description
 * Date 2014/10/8
 *
 * @author huanwuji
 */
@Entity(name = "user")
public class User {
    @Id
    @Column(length = 20)
    private String username;
    @Column(length = 20, nullable = false)
    private String password;
    @Column(length = 20)
    private String name;
    @Column(length = 10)
    private String role;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
