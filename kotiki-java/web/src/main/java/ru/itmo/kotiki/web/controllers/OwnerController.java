package ru.itmo.kotiki.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.interfaces.OwnerService;
import ru.itmo.kotiki.models.Owner;
import ru.itmo.kotiki.web.Converter;
import ru.itmo.kotiki.web.models.OwnerDto;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    private final Converter converter;

    @Autowired
    public OwnerController(OwnerService ownerService, Converter converter) {
        this.ownerService = ownerService;
        this.converter = converter;
    }

    @GetMapping("/{id}")
    public OwnerDto getOwnerById(@PathVariable int id) {
        return converter.convertToWebOwner(ownerService.findOwner(id));
    }

    @GetMapping("/all")
    public List<OwnerDto> getAllOwners() {
        return converter.convertListOfOwners(ownerService.findAllOwners());
    }

    @PostMapping("/create")
    public OwnerDto createOwner(String name, Timestamp birthday) {
        Owner owner = new Owner(name, birthday);
        ownerService.saveOwner(owner);
        return converter.convertToWebOwner(owner);
    }

    @PutMapping("/update")
    public OwnerDto updateOwner(int id, String name) {
        Owner owner = ownerService.findOwner(id);
        owner.setName(name);
        ownerService.saveOwner(owner);
        return converter.convertToWebOwner(owner);
    }

    @DeleteMapping("/del")
    public void deleteOwner(int id) {
        ownerService.deleteOwner(ownerService.findOwner(id));
    }
}
