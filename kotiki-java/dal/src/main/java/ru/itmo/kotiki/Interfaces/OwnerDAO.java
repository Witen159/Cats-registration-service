package ru.itmo.kotiki.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;

import java.util.List;

@Repository
public interface OwnerDAO extends JpaRepository<Owner, Integer> {
}
