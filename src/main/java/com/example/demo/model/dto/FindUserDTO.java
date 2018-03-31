package com.example.demo.model.dto;

public class FindUserDTO {

    private String username;
    private String queryParam;
    private boolean friends;

    public FindUserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(String queryParam) {
        this.queryParam = queryParam;
    }

    public boolean isFriends() {
        return friends;
    }

    public void setFriends(boolean friends) {
        this.friends = friends;
    }
}
