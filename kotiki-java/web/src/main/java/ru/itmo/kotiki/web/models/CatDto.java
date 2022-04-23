package ru.itmo.kotiki.web.models;

import ru.itmo.kotiki.accessory.Color;

import java.sql.Timestamp;

public class CatDto {
    private final String name;
    private final Timestamp birthday;
    private final String breed;
    private final Color color;

    public CatDto(String name, Timestamp birthday, String breed, Color color) {
        this.name = name;
        this.breed = breed;
        this.birthday = birthday;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public String getBreed() {
        return breed;
    }

    public Color getColor() {
        return color;
    }
}
