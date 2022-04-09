package ru.itmo.kotikijava.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.interfaces.IOwnerService;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;
import ru.itmo.kotiki.services.OwnerService;
import ru.itmo.kotikijava.web.Converter;
import ru.itmo.kotikijava.web.models.WebCat;
import ru.itmo.kotikijava.web.models.WebOwner;

import java.util.List;

@RestController
@ComponentScan(basePackages = {"ru.itmo.kotiki.services"})
@RequestMapping("/owners")
public class OwnerController {
    @Qualifier("OwnerService")
    private final IOwnerService ownerService;

    private final Converter converter = new Converter();

    @Autowired
    public OwnerController(IOwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/getOwner/{id}")
    public WebOwner getOwnerById(@PathVariable int id) {
        return converter.convertToWebOwner(ownerService.findOwner(id));
    }

    @GetMapping("/getOwners")
    public List<WebOwner> getAllOwners() {
        return converter.convertListOfOwners(ownerService.findAllOwners());
    }

    @PostMapping("/create")
    public void createOwner (@RequestBody WebOwner webOwner) {
        ownerService.saveOwner(converter.convertToOwner(webOwner));
    }

    @PutMapping("/put")
    public void updateOwner(@RequestBody WebOwner webOwner) {
        Owner owner = converter.convertToOwner(webOwner);
        ownerService.saveOwner(owner);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOwner(@PathVariable int id) {
        ownerService.deleteOwner(ownerService.findOwner(id));
    }
}
