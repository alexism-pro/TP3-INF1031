package com.example.tp3.Models;

import java.sql.Time;

public class Alarm {
    private String _name;
    private Time _time;

    public Alarm(String name, Time time){
        _name = name;
        _time = time;
    }
}
