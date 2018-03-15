package com.example.demo.domain.entity;


import com.example.demo.domain.base.DomainBase;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User extends DomainBase {

    private static final long serialVersionUID = 2353528370345499815L;
    private Long id;
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private String city;
    private String phonenumber;
    private Date lastPasswordReset;
    private String authorities;
    private boolean enabled;

    public User() {
        super();
        this.enabled = false;
    }

    public User(String username, String password, String email, String firstname, String lastname, String city, String phonenumber, Date lastPasswordReset, String authorities) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.phonenumber = phonenumber;
        this.lastPasswordReset = lastPasswordReset;
        this.authorities = authorities;
        this.enabled = false;
    }


    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	@SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize = 1)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(name = "username", unique = true)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email", unique = true)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "phone_number")
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Column(name = "last_password_reset")
    public Date getLastPasswordReset() {
        return this.lastPasswordReset;
    }

    public void setLastPasswordReset(Date lastPasswordReset) {
        this.lastPasswordReset = lastPasswordReset;
    }

    @Column(name = "authorities")
    public String getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    @Column(name = "enabled")
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
