package ru.itmo.kotiki.interfaces;

import org.springframework.stereotype.Service;
import ru.itmo.kotiki.accessory.Color;
import ru.itmo.kotiki.models.Cat;

import java.util.ArrayList;
import java.util.List;

@Service
public interface ICatService {
    public Cat findCat(int id);

    public void saveCat(Cat cat);

    public void deleteCat(Cat cat);

    public List<Cat> findAllCats();

    public List<Cat> findCatsByColor(Color color);
}
