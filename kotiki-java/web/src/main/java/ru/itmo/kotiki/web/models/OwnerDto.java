package ru.itmo.kotiki.web.models;

import java.sql.Timestamp;

public class OwnerDto {
    private final String name;
    private final Timestamp birthday;

    public OwnerDto(String name, Timestamp dateOfBirth) {
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
