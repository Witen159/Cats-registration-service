package ru.itmo.kotiki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.Interfaces.CatDAO;
import ru.itmo.kotiki.accessory.Color;
import ru.itmo.kotiki.interfaces.CatService;
import ru.itmo.kotiki.models.Cat;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatServiceImpl implements CatService {
    private final CatDAO catDAO;

    @Autowired
    public CatServiceImpl(CatDAO catDAO) {
        this.catDAO = catDAO;
    }

    public Cat findCat(int id) {
        return catDAO.getById(id);
    }

    public void saveCat(Cat cat) {
        catDAO.save(cat);
    }

    public void deleteCat(Cat cat) {
        catDAO.delete(cat);
    }

    public List<Cat> findAllCats() {
        return catDAO.findAll();
    }

    public List<Cat> findCatsByColor(Color color) {
        List<Cat> coloredCats = new ArrayList<>();
        for (Cat cat : findAllCats()) {
            if (cat.getColor() == color)
                coloredCats.add(cat);
        }
        return coloredCats;
    }
}
