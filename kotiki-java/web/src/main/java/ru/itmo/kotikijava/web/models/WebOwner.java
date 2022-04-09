package ru.itmo.kotikijava.web.models;

import java.sql.Timestamp;

public class WebOwner {
    private final String name;
    private final Timestamp birthday;

    public WebOwner (String name, Timestamp dateOfBirth) {
        this.name = name;
        this.birthday = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public Timestamp getBirthday() {
        return birthday;
    }
}
