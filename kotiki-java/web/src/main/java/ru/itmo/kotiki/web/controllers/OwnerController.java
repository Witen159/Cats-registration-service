package ru.itmo.kotiki.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.interfaces.OwnerService;
import ru.itmo.kotiki.models.Owner;
import ru.itmo.kotiki.web.Converter;
import ru.itmo.kotiki.web.models.OwnerDto;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    private final Converter converter = new Converter();

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/getOwner/{id}")
    public OwnerDto getOwnerById(@PathVariable int id) {
        return converter.convertToWebOwner(ownerService.findOwner(id));
    }

    @GetMapping("/getOwners")
    public List<OwnerDto> getAllOwners() {
        return converter.convertListOfOwners(ownerService.findAllOwners());
    }

    @PostMapping("/create")
    public void createOwner(@RequestBody OwnerDto webOwner) {
        ownerService.saveOwner(converter.convertToOwner(webOwner));
    }

    @PutMapping("/put")
    public void updateOwner(@RequestBody OwnerDto webOwner) {
        Owner owner = converter.convertToOwner(webOwner);
        ownerService.saveOwner(owner);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOwner(@PathVariable int id) {
        ownerService.deleteOwner(ownerService.findOwner(id));
    }
}