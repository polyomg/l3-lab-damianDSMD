package org.example.lab7.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Double price;

    @Temporal(TemporalType.DATE)
    @Column(name = "createdate")
    private Date createDate = new Date();

    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;
}
