package ru.itmo.kotiki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.Interfaces.CatDAO;
import ru.itmo.kotiki.Interfaces.UserDAO;
import ru.itmo.kotiki.accessory.Color;
import ru.itmo.kotiki.accessory.Role;
import ru.itmo.kotiki.interfaces.CatService;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CatServiceImpl implements CatService {
    private final CatDAO catDAO;
    private final UserDAO userDAO;

    @Autowired
    public CatServiceImpl(CatDAO catDAO, UserDAO userDAO) {
        this.catDAO = catDAO;
        this.userDAO = userDAO;
    }

    public Cat findCat(int id) {
        Cat cat = catDAO.getById(id);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        User user = userDAO.findUserByUsername(username);

        if (user.getRole() == Role.ROLE_ADMIN || Objects.equals(user.getUsername(), cat.getOwner().getUsername())) {
            return cat;
        }
        return null;
    }

    public void saveCat(Cat cat) {
        catDAO.save(cat);
    }

    public void deleteCat(Cat cat) {
        catDAO.delete(cat);
    }

    public List<Cat> findAllCats() {
        List<Cat> cats = new ArrayList<>();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User user = userDAO.findUserByUsername(username);
        for (Cat cat : catDAO.findAll()) {
            if (user.getRole() == Role.ROLE_ADMIN || Objects.equals(user.getUsername(), cat.getOwner().getUsername())) {
                cats.add(cat);
            }
        }
        return cats;
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
