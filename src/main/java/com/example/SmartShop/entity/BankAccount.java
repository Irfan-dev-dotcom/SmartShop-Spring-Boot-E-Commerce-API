package com.example.SmartShop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String accountNumber;
    private double balance;

    @ManyToOne
    private User user;
}
