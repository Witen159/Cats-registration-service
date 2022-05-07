package ru.itmo.kotiki.web.models;

import java.sql.Timestamp;

public class OwnerDto {
    private final int id;
    private final String name;
    private final Timestamp birthday;

    public OwnerDto(int id, String name, Timestamp dateOfBirth) {
        this.id = id;
        this.name = name;
        this.birthday = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Timestamp getBirthday() {
        return birthday;
    }
}
