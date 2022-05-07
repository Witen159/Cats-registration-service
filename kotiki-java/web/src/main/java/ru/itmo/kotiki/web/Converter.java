package ru.itmo.kotiki.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;
import ru.itmo.kotiki.web.models.CatDto;
import ru.itmo.kotiki.web.models.OwnerDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class Converter {
    @Autowired
    public Converter() {
    }

    public Cat convertToCat(CatDto webCat) {
        return new Cat(webCat.getName(), webCat.getBirthday(), webCat.getBreed(), webCat.getColor());
    }

    public CatDto convertToWebCat(Cat cat) {
        return new CatDto(cat.getId(), cat.getName(), cat.getBirthday(), cat.getBreed(), cat.getColor());
    }

    public List<CatDto> convertListOfCats(List<Cat> cats) {
        List<CatDto> webCats = new ArrayList<>();
        for (Cat cat : cats) {
            webCats.add(convertToWebCat(cat));
        }
        return webCats;
    }

    public Owner convertToOwner(OwnerDto webOwner) {
        return new Owner(webOwner.getName(), webOwner.getBirthday());
    }

    public OwnerDto convertToWebOwner(Owner owner) {
        return new OwnerDto(owner.getId(), owner.getName(), owner.getBirthday());
    }

    public List<OwnerDto> convertListOfOwners(List<Owner> owners) {
        List<OwnerDto> webOwners = new ArrayList<>();
        for (Owner owner : owners) {
            webOwners.add(convertToWebOwner(owner));
        }
        return webOwners;
    }
}
