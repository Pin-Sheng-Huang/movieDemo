package com.example.moviedemo.bean;

import java.util.Date;

/**
 * @program: movieDemo
 * @description:
 * @Creator: 阿昇
 * @CreateTime: 2023-05-07 18:48
 * @LastEditTime: 2023-05-07 18:48
 */

public class Movie {
    private String name;
    private String actor;
    private double score;
    private double time;
    private double price;
    private int number;
    private Date starttime;

    public Movie() {
    }

    public Movie(String name, String actor,  double time, double price, int number, Date starttime) {
        this.name = name;
        this.actor = actor;
        this.score = score;
        this.time = time;
        this.price = price;
        this.number = number;
        this.starttime = starttime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }
}
