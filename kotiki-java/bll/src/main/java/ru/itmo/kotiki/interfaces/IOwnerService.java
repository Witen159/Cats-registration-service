package ru.itmo.kotiki.interfaces;

import org.springframework.stereotype.Service;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;

import java.util.List;

@Service
public interface IOwnerService {
    public Owner findOwner(int id);

    public void saveOwner(Owner owner);

    public void deleteOwner(Owner owner);

    public List<Owner> findAllOwners();

    public Cat findCatById(int ownerId, int catId);

    public List<Cat> getAllOwnersCats(int id);
}
