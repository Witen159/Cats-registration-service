package ru.itmo.kotiki.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.accessory.Color;
import ru.itmo.kotiki.interfaces.CatService;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.web.Converter;
import ru.itmo.kotiki.web.models.CatDto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/cats")
public class CatController {
    private final CatService catService;

    private final Converter converter;

    @Autowired
    public CatController(CatService catService, Converter converter) {
        this.catService = catService;
        this.converter = converter;
    }

    @GetMapping("/{id}")
    public CatDto getCatById(@PathVariable Integer id) {
        Cat cat = catService.findCat(id);
        if (cat != null) {
            return converter.convertToWebCat(cat);
        }
        return null;
    }

    @GetMapping("/all")
    public List<CatDto> getAllCats() {
        return converter.convertListOfCats(catService.findAllCats());
    }

    @PostMapping("/create")
    public CatDto createCat(String name, Timestamp birthday, String breed, Color color) {
        Cat cat = new Cat(name, birthday, breed, color);
        catService.saveCat(cat);
        return converter.convertToWebCat(cat);
    }

    @PutMapping("/update")
    public CatDto updateCat(int id, String name, Color color) {
        Cat cat = catService.findCat(id);
        if (cat != null) {
            cat.setName(name);
            cat.setColor(color);
            catService.saveCat(cat);
            return converter.convertToWebCat(cat);
        }
        return null;
    }

    @DeleteMapping("/del")
    public void deleteCat(int id) {
        catService.deleteCat(catService.findCat(id));
    }
}

