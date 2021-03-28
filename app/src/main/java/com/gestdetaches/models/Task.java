package com.gestdetaches.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Create by  on
 */
@Getter
@Setter
public class Task {

    private int id;
    private String titel;
    private String date;
    private String time;
    private String description;

    public Task()
    {

    }
    public Task (int id, String titel, String date, String time, String description)
    {
        this.id = id;
        this.titel = titel;
        this.date  = date;
        this.time  = time;
        this.description = description;
    }

    public Task(String titel,
                String date,
                String time,
                String description) {
        this.titel = titel;
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public Task(String titel,
                String date,
                String time) {
        this.titel = titel;
        this.date = date;
        this.time = time;

    }

    public String toString(){
        return titel + "  le  " + date + "  a  " + time;
    }
}
