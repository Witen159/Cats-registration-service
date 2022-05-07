package ru.itmo.kotiki.web.models;

import ru.itmo.kotiki.accessory.Color;

import java.sql.Timestamp;

public class CatDto {
    private final int id;
    private final String name;
    private final Timestamp birthday;
    private final String breed;
    private final Color color;

    public CatDto(int id, String name, Timestamp birthday, String breed, Color color) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.birthday = birthday;
        this.color = color;
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

    public String getBreed() {
        return breed;
    }

    public Color getColor() {
        return color;
    }
}
