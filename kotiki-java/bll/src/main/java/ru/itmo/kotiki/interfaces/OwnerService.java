package ru.itmo.kotiki.interfaces;

import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;

import java.util.List;

public interface OwnerService {
    public Owner findOwner(int id);
    public void saveOwner(Owner owner);
    public void deleteOwner(Owner owner);
    public void updateOwner(Owner owner);
    public List<Owner> findAllOwners();
    public Cat findCatById(int id);
    public List<Cat> getAllOwnersCats (int id);
}
