package org.example.lab6.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.lab6.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, String> {}
