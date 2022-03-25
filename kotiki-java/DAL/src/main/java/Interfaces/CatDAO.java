package Interfaces;

import models.Cat;
import models.Owner;

import java.util.List;

public interface CatDAO {
    public Cat findById (int id);
    public void save (Cat cat);
    public void update(Cat cat);
    public void delete(Cat cat);
    public Cat findFriendById(int id);
    public List<Cat> findAll();
}
