package com.learn.core.bean;

public class Work {
    private Integer id;

    private String homework;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework == null ? null : homework.trim();
    }
}