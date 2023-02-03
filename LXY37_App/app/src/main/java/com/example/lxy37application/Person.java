package com.example.lxy37application;


import java.io.Serializable;

public class Person implements Serializable {

    private String username;
    private String password;

    public Person(String name, String password){
        this.username = name;
        this.password = password;
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

    public String toString(){
        String t;
        t ="{username:%s,password:%s}";
        t =String.format(t,this.username,this.password);
        return t;
    }

}

