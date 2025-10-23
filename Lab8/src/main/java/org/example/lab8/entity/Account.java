package org.example.lab8.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @Column(length = 100)
    private String username;

    private String password;
    private String fullname;
    private boolean admin; // role flag
}
