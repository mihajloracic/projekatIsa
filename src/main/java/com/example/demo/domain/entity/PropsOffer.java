package com.example.demo.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "propsOffer")
public class PropsOffer {
    private long id;
    private Props props;
    private User user;
    private double value;
    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public PropsOffer() {

    }
    public PropsOffer(Props props, User user, double value) {
        this.props = props;
        this.user = user;
        this.value = value;
    }
    @Column(name = "value")
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setId(long id) {
        this.id = id;
    }
    @ManyToOne
    public Props getProps() {
        return props;
    }

    public void setProps(Props props) {
        this.props = props;
    }
    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
