package com.workintech.s18d2.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "vegetable", schema = "fsweb")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vegetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "is_grown_on_tree")
    private boolean grownOnTree;
}
