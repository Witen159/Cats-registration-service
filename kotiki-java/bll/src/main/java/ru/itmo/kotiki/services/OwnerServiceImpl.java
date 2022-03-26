package ru.itmo.kotiki.services;

import ru.itmo.kotiki.Interfaces.OwnerDAO;
import ru.itmo.kotiki.interfaces.OwnerService;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;
import ru.itmo.kotiki.repository.OwnerDAOImpl;

import java.util.List;

public class OwnerServiceImpl implements OwnerService {
    private OwnerDAO ownerDAO = new OwnerDAOImpl();

    @Override
    public Owner findOwner(int id) {
        return ownerDAO.findById(id);
    }

    @Override
    public void saveOwner(Owner owner) {
        ownerDAO.save(owner);
    }

    @Override
    public void deleteOwner(Owner owner) {
        ownerDAO.delete(owner);
    }

    @Override
    public void updateOwner(Owner owner) {
        ownerDAO.update(owner);
    }

    @Override
    public List<Owner> findAllOwners() {
        return ownerDAO.findAll();
    }

    @Override
    public Cat findCatById(int id) {
        return ownerDAO.findCatById(id);
    }

    @Override
    public List<Cat> getAllOwnersCats(int id) {
        return findOwner(id).getCats();
    }
}
