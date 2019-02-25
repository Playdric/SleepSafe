package com.team.dream.sleepsafe.chat.chatApplication.entity;

public class Users {
    private String id;
    private String pseudo;
    private String email;

    public Users() { }

    public Users(String id, String pseudo) {
        this.id = id;
        this.pseudo = pseudo;
    }

    public Users(String id, String pseudo, String email) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}