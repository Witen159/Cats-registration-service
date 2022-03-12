package interfaces;

import models.Cat;
import models.Owner;

import java.util.List;

public interface CatService {
    public Cat findCat(int id);
    public void saveCat(Cat cat);
    public void deleteCat(Cat cat);
    public void updateCat(Cat cat);
    public List<Cat> findAllCats();
    public Cat findCatById(int id);
}
