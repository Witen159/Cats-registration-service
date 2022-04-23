package ru.itmo.kotiki.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.interfaces.CatService;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.web.models.CatDto;
import ru.itmo.kotiki.web.Converter;

import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatController {
    private final CatService catService;

    private final Converter converter = new Converter();

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/getCat/{id}")
    public CatDto getCatById(@PathVariable Integer id) {
        return converter.convertToWebCat(catService.findCat(id));
    }

    @GetMapping("/getCats")
    public List<CatDto> getAllCats() {
        return converter.convertListOfCats(catService.findAllCats());
    }

    @PostMapping("/create")
    public void createCat(@RequestBody CatDto webCat) {
        Cat cat = converter.convertToCat(webCat);
        catService.saveCat(cat);
    }

    @PutMapping("/put")
    public void updateCat(@RequestBody CatDto webCat) {
        Cat cat = converter.convertToCat(webCat);
        catService.saveCat(cat);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCat(@PathVariable int id) {
        catService.deleteCat(catService.findCat(id));
    }
}

