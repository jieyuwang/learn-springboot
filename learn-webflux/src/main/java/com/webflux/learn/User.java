package com.webflux.learn;


/**
 * @author Created by 一伞烟雨 on 2018/3/3.
 */

public class User {
    public User(){}

    public User(Long id, String user) {
        this.id = id;
        this.user = user;
    }

    private Long id;
    private String user;

    public Long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
}

