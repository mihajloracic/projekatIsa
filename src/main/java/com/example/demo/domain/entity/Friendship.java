package com.example.demo.domain.entity;

import javax.persistence.*;

@Entity
@Table(
        name = "friendship",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_one_id", "user_two_id"})}
)
public class Friendship {

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_one_id")   // always smaller than userTwoId
    private Long userOneId;

    @Column(name = "user_two_id")
    private Long userTwoId;

    /**
     * 0 - pending
     * 1 - accepted
     * 2 - declined
     */
    @Column(name = "status")
    private int status;


    @Column(name = "action_user_id")
    private Long actionUserId;     // id of user who performed most recent status field update


    public Friendship() {
    }

    public Friendship(Long userOneId, Long userTwoId, int status, Long actionUserId) {
        this.userOneId = userOneId;
        this.userTwoId = userTwoId;
        this.status = status;
        this.actionUserId = actionUserId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserOneId() {
        return userOneId;
    }

    public void setUserOneId(Long userOneId) {
        this.userOneId = userOneId;
    }

    public Long getUserTwoId() {
        return userTwoId;
    }

    public void setUserTwoId(Long userTwoId) {
        this.userTwoId = userTwoId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getActionUserId() {
        return actionUserId;
    }

    public void setActionUserId(Long actionUserId) {
        this.actionUserId = actionUserId;
    }
}