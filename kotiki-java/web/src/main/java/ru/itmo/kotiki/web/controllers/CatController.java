package ru.itmo.kotiki.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.interfaces.CatService;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.web.Converter;
import ru.itmo.kotiki.web.models.CatDto;

import java.util.List;

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
        return converter.convertToWebCat(catService.findCat(id));
    }

    @GetMapping("/all")
    public List<CatDto> getAllCats() {
        return converter.convertListOfCats(catService.findAllCats());
    }

    @PostMapping("/")
    public void createCat(@RequestBody CatDto webCat) {
        Cat cat = converter.convertToCat(webCat);
        catService.saveCat(cat);
    }

    @PutMapping("/")
    public void updateCat(@RequestBody CatDto webCat) {
        Cat cat = converter.convertToCat(webCat);
        catService.saveCat(cat);
    }

    @DeleteMapping("/{id}")
    public void deleteCat(@PathVariable int id) {
        catService.deleteCat(catService.findCat(id));
    }
}

