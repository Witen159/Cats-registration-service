package models;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column (name = "birthday")
    private Date dateOfBirth;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Cat> cats;

    public Owner() {}
    public Owner (String name, Date dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        cats = new ArrayList<>();
    }

    public void addCat(Cat cat) {
        cat.setOwner(this);
        cats.add(cat);
    }

    public void removeCat(Cat cat) {
        cats.remove(cat);
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }
}
