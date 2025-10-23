package org.example.lab5.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @Transient  // Not stored in database
    private int qty = 0;

    // Constructor without qty (for database)
    public Item(Integer id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = 0;
    }
}