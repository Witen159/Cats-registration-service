package ru.itmo.kotiki;

import ru.itmo.kotiki.accessory.Color;
import ru.itmo.kotiki.interfaces.CatService;
import ru.itmo.kotiki.interfaces.OwnerService;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;
import ru.itmo.kotiki.services.CatServiceImpl;
import ru.itmo.kotiki.services.OwnerServiceImpl;

import java.sql.Timestamp;

public class Main {
    public static void main(String[] args) {
        OwnerService ownerService = new OwnerServiceImpl();
        CatService catService = new CatServiceImpl();
        Owner owner = new Owner("Den", new Timestamp(1000000));
        ownerService.saveOwner(owner);
        Cat cat1 = new Cat("Shu", new Timestamp(1000000), "breed1", Color.BLACK);
        Cat cat2 = new Cat("Bu", new Timestamp(1001000), "breed2", Color.RED);
        owner.addCat(cat1);
        owner.addCat(cat2);
        ownerService.updateOwner(owner);
        for (Cat cat : catService.findCatsByColor(Color.BLACK)) {
            System.out.println(cat.getName());
        }
    }
}
