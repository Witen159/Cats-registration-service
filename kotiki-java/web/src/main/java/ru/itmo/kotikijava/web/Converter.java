package ru.itmo.kotikijava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;
import ru.itmo.kotikijava.web.models.WebCat;
import ru.itmo.kotikijava.web.models.WebOwner;

import java.util.ArrayList;
import java.util.List;

@Component
public class Converter {
    @Autowired
    public Converter() {}
    public Cat convertToCat (WebCat webCat) {
        return new Cat(webCat.getName(), webCat.getBirthday(), webCat.getBreed(), webCat.getColor());
    }

    public WebCat convertToWebCat(Cat cat) {
        return new WebCat(cat.getName(), cat.getBirthday(), cat.getBreed(), cat.getColor());
    }

    public List<WebCat> convertListOfCats(List<Cat> cats) {
        List<WebCat> webCats = new ArrayList<>();
        for (Cat cat : cats) {
            webCats.add(convertToWebCat(cat));
        }
        return webCats;
    }

    public Owner convertToOwner (WebOwner webOwner) {
        return new Owner(webOwner.getName(), webOwner.getBirthday());
    }

    public WebOwner convertToWebOwner (Owner owner) {
        return new WebOwner(owner.getName(), owner.getBirthday());
    }

    public List<WebOwner> convertListOfOwners(List<Owner> owners) {
        List<WebOwner> webOwners = new ArrayList<>();
        for (Owner owner : owners) {
            webOwners.add(convertToWebOwner(owner));
        }
        return webOwners;
    }
}
