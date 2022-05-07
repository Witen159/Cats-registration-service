package ru.itmo.kotiki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.Interfaces.OwnerDAO;
import ru.itmo.kotiki.interfaces.OwnerService;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;

import java.util.List;
import java.util.Objects;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerDAO ownerDAO;

    @Autowired
    public OwnerServiceImpl(OwnerDAO ownerDAO) {
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

    public List<Cat> getAllOwnersCats(int id) {
        return findOwner(id).getCats();
    }
}
