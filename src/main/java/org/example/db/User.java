package org.example.db;

import java.time.Instant;

public class User {


    int tickets;
    Long userId;
    String username;
    String password;

    String email;
    Instant createdOn;
    Instant lastLogin;
    int price;



    public User(Long userId, String username, String password, String email, Instant createdOn, Instant lastLogin, int price
    , int tickets) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdOn = createdOn;
        this.lastLogin = lastLogin;
        this.price = price;
        this.tickets = tickets;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Instant getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Instant lastLogin) {
        this.lastLogin = lastLogin;
    }


}
