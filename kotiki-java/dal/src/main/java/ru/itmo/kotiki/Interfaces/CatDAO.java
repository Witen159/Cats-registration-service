package ru.itmo.kotiki.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki.models.Cat;

import java.util.List;

@Repository
public interface CatDAO extends JpaRepository<Cat, Integer> {
    List<Cat> findCatsByOwnerId(int owner_id);
}
