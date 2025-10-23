package org.example.lab7.dao;

import org.example.lab7.entity.Product;
import org.example.lab7.entity.Report;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {

    // Bài 1
    @Query("FROM Product o WHERE o.price BETWEEN ?1 AND ?2")
    List<Product> findByPrice(double minPrice, double maxPrice);

    // Bài 2
    @Query("FROM Product o WHERE o.name LIKE ?1")
    Page<Product> findByKeywords(String keywords, Pageable pageable);

    // Bài 3
    @Query("SELECT o.category AS group, SUM(o.price) AS sum, COUNT(o) AS count " +
            "FROM Product o " +
            "GROUP BY o.category " +
            "ORDER BY SUM(o.price) DESC")
    List<Report> getInventoryByCategory();

    // Bài 4 DSL
    List<Product> findByPriceBetween(double min, double max);

    // Bài 5 DSL
    Page<Product> findAllByNameLike(String keywords, Pageable pageable);
}
