package ru.itmo.kotiki.services;

import ru.itmo.kotiki.Interfaces.CatDAO;
import ru.itmo.kotiki.accessory.Color;
import ru.itmo.kotiki.interfaces.CatService;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.repository.CatDAOImpl;

import java.util.ArrayList;
import java.util.List;

public class CatServiceImpl implements CatService {
    private CatDAO catDAO = new CatDAOImpl();
    @Override
    public Cat findCat(int id) {
        return catDAO.findById(id);
    }

    @Override
    public void saveCat(Cat cat) {
        catDAO.save(cat);
    }

    @Override
    public void deleteCat(Cat cat) {
        catDAO.delete(cat);
    }

    @Override
    public void updateCat(Cat cat) {
        catDAO.update(cat);
    }

    @Override
    public List<Cat> findAllCats() {
        return catDAO.findAll();
    }

    @Override
    public List<Cat> findCatsByColor(Color color) {
        List<Cat> coloredCats = new ArrayList<>();
        for (Cat cat : findAllCats()) {
            if (cat.getColor() == color)
                coloredCats.add(cat);
        }
        return coloredCats;
    }

    @Override
    public Cat findCatById(int id) {
        return catDAO.findFriendById(id);
    }
}
