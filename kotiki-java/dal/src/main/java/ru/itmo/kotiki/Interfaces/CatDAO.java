package ru.itmo.kotiki.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki.models.Cat;

@Repository
public interface CatDAO extends JpaRepository<Cat, Integer> {
}
