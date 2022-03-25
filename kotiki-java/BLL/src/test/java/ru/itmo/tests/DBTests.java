package ru.itmo.tests;

import Interfaces.CatDAO;
import accessory.Color;
import interfaces.CatService;
import interfaces.OwnerService;
import models.Cat;
import models.Owner;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import services.CatServiceImpl;
import services.OwnerServiceImpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DBTests {
    private Session session;
    private OwnerService ownerService;
    private CatService catService;

    @Before
    public void setup() {
        session = mock(Session.class);
        ownerService = mock(OwnerServiceImpl.class);
        catService = mock(CatServiceImpl.class);
    }

    @Test
    public void findByIdTest() {
        Owner owner = new Owner("Den", new Timestamp(1000000));
        Cat cat = new Cat("Shu", new Timestamp(1000000), "breed", Color.Black);
        owner.addCat(cat);

        when(ownerService.findOwner(1)).thenReturn(owner);
        when(catService.findCat(1)).thenReturn(cat);

        assertEquals(ownerService.findOwner(1).getName(), "Den");
        assertEquals(catService.findCat(1).getName(), "Shu");
    }

    @Test
    public void getCatFromOwner_GetOwnerFromCatTest() {
        Owner owner = new Owner("Den", new Timestamp(1000000));
        Cat cat = new Cat("Shu", new Timestamp(1000000), "breed", Color.Black);
        owner.addCat(cat);
        when(ownerService.findCatById(1)).thenReturn(cat);
        assertEquals(ownerService.findCatById(1).getOwner().getName(), "Den");
    }
}
