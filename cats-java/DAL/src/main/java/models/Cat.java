package models;

import accessory.Color;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column (name = "birthday")
    private Date dateOfBirth;
    private String breed;
    private Color color;
    /* @OneToMany(mappedBy = "cat", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Cat> friends;*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Cat() {}
    public Cat(String name, Date dateOfBirth, String breed, Color color) {
        this.name = name;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.color = color;
        // friends = new ArrayList<>();
    }

//    public void addFriend(Cat cat) {
//        friends.add(cat);
//    }
//
//    public void removeFriend(Cat cat) {
//        friends.remove(cat);
//    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

//    public List<Cat> getFriends() {
//        return friends;
//    }
//
//    public void setFriends(List<Cat> friends) {
//        this.friends = friends;
//    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
