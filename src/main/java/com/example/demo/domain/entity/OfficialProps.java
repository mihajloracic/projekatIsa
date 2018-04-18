package com.example.demo.domain.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "officialprops")
public class OfficialProps implements Serializable {

    private long id;
    private String name;
    private String description;
    private Date expirationDate;
    private String imageUrl;
    private User userCreated;
    private Venue venu;

    @ManyToOne
    public User getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(User userCreated) {
        this.userCreated = userCreated;
    }

    public OfficialProps() {
    }

    public OfficialProps(String name, String description, String imageUrl, Date date, Venue venue) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.expirationDate = date;
        this.venu = venu;
    }
    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    @ManyToOne
    public Venue getVenu() {
        return venu;
    }

    public void setVenu(Venue venu) {
        this.venu = venu;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Column(name = "expdate")
    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    @Column(name = "imageurl")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}

