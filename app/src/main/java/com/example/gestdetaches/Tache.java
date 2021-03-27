package com.example.gestdetaches;

/**
 * Create by  on
 */
public class Tache {

    String titel;
    String date;
    String time;
    public Tache(String titel,
            String date,
            String time) {
        this.titel = titel;
        this.date = date;
        this.time = time;
    }

    public String afficherTache(){
        return titel + "  le  " + date + "  a  " + time;
    }
}
