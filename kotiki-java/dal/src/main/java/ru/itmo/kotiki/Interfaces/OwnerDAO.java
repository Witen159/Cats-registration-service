package ru.itmo.kotiki.Interfaces;

import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;

import java.util.List;

public interface OwnerDAO {
    public Owner findById (int id);
    public void save (Owner owner);
    public void update(Owner owner);
    public void delete(Owner owner);
    public Cat findCatById(int id);
    public List<Owner> findAll();
}
