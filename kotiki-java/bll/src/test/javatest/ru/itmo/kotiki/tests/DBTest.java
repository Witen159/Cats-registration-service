package ru.itmo.kotiki.tests;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import ru.itmo.kotiki.accessory.Color;
import ru.itmo.kotiki.interfaces.CatService;
import ru.itmo.kotiki.interfaces.OwnerService;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DBTest {
    private Session session;
    private OwnerService ownerService;
    private CatService catService;

    @Before
    public void setup() {
        session = mock(Session.class);
        ownerService = mock(OwnerService.class);
        catService = mock(CatService.class);
    }

    @Test
    public void findByIdTest() {
        Owner owner = new Owner("Den", new Timestamp(1000000));
        Cat cat = new Cat("Shu", new Timestamp(1000000), "breed", Color.BLACK);
        owner.addCat(cat);

        when(ownerService.findOwner(1)).thenReturn(owner);
        when(catService.findCat(1)).thenReturn(cat);

        assertEquals(ownerService.findOwner(1).getName(), "Den");
        assertEquals(catService.findCat(1).getName(), "Shu");
    }

    @Test
    public void getCatFromOwner_GetOwnerFromCatTest() {
        Owner owner = new Owner("Den", new Timestamp(1000000));
        Cat cat = new Cat("Shu", new Timestamp(1000000), "breed", Color.BLACK);
        owner.addCat(cat);
        when(catService.findCat(1)).thenReturn(cat);
        assertEquals(catService.findCat(1).getOwner().getName(), "Den");
    }
}
