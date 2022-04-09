package ru.itmo.kotikijava.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.interfaces.ICatService;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.services.CatService;
import ru.itmo.kotikijava.web.Converter;
import ru.itmo.kotikijava.web.models.WebCat;

import java.util.List;

@RestController
@ComponentScan(basePackages = {"ru.itmo.kotiki.services"})
@RequestMapping("/cats")
public class CatController {
    @Qualifier("CatService")
    private final ICatService catService;

    private final Converter converter = new Converter();

    @Autowired
    public CatController(ICatService catService) {
        this.catService = catService;
    }

    @GetMapping("/get/cat/{id}")
    public WebCat getCatById(@PathVariable Integer id) {
        return converter.convertToWebCat(catService.findCat(id));
    }

    @GetMapping("/get/cats")
    public List<WebCat> getAllCats() {
        return converter.convertListOfCats(catService.findAllCats());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCat(@RequestBody WebCat webCat) {
        Cat cat = converter.convertToCat(webCat);
        catService.saveCat(cat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/put")
    public ResponseEntity<?> updateCat(@RequestBody WebCat webCat) {
        Cat cat = converter.convertToCat(webCat);
        catService.saveCat(cat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCat(@RequestBody WebCat webCat) {
        Cat cat = converter.convertToCat(webCat);
        catService.deleteCat(cat);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

