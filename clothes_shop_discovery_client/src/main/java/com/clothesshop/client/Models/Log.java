package com.clothesshop.client.Models;

public class Log {
    private Integer id;
    private String message;
    private String actionType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String action) {
        this.actionType = action;
    }
}
