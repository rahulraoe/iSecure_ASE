package com.rahul.isecure;

public class User {
    String name;
    String email;
    String date;
    String uid;
String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User(String name, String email, String date, String uid, String address) {
        this.name = name;
        this.email = email;
        this.date = date;
        this.uid = uid;
        this.address = address;
    }

    public User(String name, String email, String date, String uid) {
        this.name = name;
        this.email = email;
        this.date = date;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
