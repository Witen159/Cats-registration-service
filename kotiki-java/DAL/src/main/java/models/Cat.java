package models;

import accessory.Color;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table (name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Timestamp birthday;
    private String breed;
    @Enumerated(value = EnumType.STRING)
    private Color color;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Cat() {}
    public Cat(String name, Timestamp birthday, String breed, Color color) {
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

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp dateOfBirth) {
        this.birthday = dateOfBirth;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
