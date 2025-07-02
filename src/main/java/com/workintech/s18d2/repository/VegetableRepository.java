package com.workintech.s18d2.repository;

import com.workintech.s18d2.entity.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VegetableRepository extends JpaRepository<Vegetable, Long> {

    @Query("SELECT v FROM Vegetable v ORDER BY v.price DESC")
    List<Vegetable> getByPriceDesc();

    @Query("SELECT v FROM Vegetable v ORDER BY v.price ASC")
    List<Vegetable> getByPriceAsc();

    @Query("SELECT v FROM Vegetable v WHERE v.name LIKE %:name%")
    List<Vegetable> searchByName(@Param("name") String name);
}
