package ru.itmo.kotiki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.Interfaces.OwnerDAO;
import ru.itmo.kotiki.interfaces.IOwnerService;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;

import java.util.List;

@Service
@ComponentScan(basePackages = {"ru.itmo.kotiki.Interfaces"})
public class OwnerService implements IOwnerService {
    @Qualifier("OwnerDAO")
    private final OwnerDAO ownerDAO;

    @Autowired
    public OwnerService(OwnerDAO ownerDAO) {
        this.ownerDAO = ownerDAO;
    }

    public Owner findOwner(int id) {
        return ownerDAO.getById(id);
    }

    public void saveOwner(Owner owner) {
        ownerDAO.save(owner);
    }

    public void deleteOwner(Owner owner) {
        ownerDAO.delete(owner);
    }

    public List<Owner> findAllOwners() {
        return ownerDAO.findAll();
    }

    public Cat findCatById(int ownerId, int catId) {
        for (Cat cat : getAllOwnersCats(ownerId)) {
            if (cat.getId() == catId)
                return cat;
        }
        return null;
    }

    public List<Cat> getAllOwnersCats(int id) {
        return findOwner(id).getCats();
    }
}
