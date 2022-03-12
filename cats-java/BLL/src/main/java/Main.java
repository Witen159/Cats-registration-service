import accessory.Color;
import interfaces.OwnerService;
import models.Cat;
import models.Owner;
import services.OwnerServiceImpl;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        OwnerService ownerService = new OwnerServiceImpl();
        Owner owner = new Owner("Den", new Date(1000000));
        ownerService.saveOwner(owner);
        Cat cat1 = new Cat("Shu", new Date(1000000), "breed1", Color.Black);
        Cat cat2 = new Cat("Bu", new Date(1001000), "breed2", Color.Red);
        owner.addCat(cat1);
        owner.addCat(cat2);
        ownerService.updateOwner(owner);
    }
}
