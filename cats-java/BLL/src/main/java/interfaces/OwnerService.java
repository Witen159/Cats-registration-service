package interfaces;

import Interfaces.OwnerDAO;
import models.Cat;
import models.Owner;

import java.util.List;

public interface OwnerService {
    public Owner findOwner(int id);
    public void saveOwner(Owner owner);
    public void deleteOwner(Owner owner);
    public void updateOwner(Owner owner);
    public List<Owner> findAllOwners();
    public Cat findCatById(int id);
}
