package org.example.model;

import java.util.Date;

public class Holiday {
    private String country;
    private Date date;
    private String name;

    public Holiday() {
    }

    public Holiday(String country, Date date, String name) {
        this.country = country;
        this.date = date;
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Holiday{" +
                "country='" + country + '\'' +
                ", date=" + date +
                ", name='" + name + '\'' +
                '}';
    }
}
