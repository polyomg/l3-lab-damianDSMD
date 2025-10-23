package org.example.lab5.repository;

import org.example.lab5.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    // Spring Data JPA provides all CRUD methods automatically
}