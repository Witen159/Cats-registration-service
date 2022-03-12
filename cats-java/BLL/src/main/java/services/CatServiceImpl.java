package services;

import Interfaces.CatDAO;
import interfaces.CatService;
import models.Cat;
import repository.CatDAOImpl;

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
    public Cat findCatById(int id) {
        return catDAO.findFriendById(id);
    }
}
