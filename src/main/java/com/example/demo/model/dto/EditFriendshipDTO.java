package com.example.demo.model.dto;

public class EditFriendshipDTO {

    private String initiatorUsername;
    private String recieverUsername;

    public EditFriendshipDTO() {
    }

    public String getInitiatorUsername() {
        return initiatorUsername;
    }

    public void setInitiatorUsername(String initiatorUsername) {
        this.initiatorUsername = initiatorUsername;
    }

    public String getRecieverUsername() {
        return recieverUsername;
    }

    public void setRecieverUsername(String recieverUsername) {
        this.recieverUsername = recieverUsername;
    }
}
