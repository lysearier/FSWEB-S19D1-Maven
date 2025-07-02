package com.workintech.s18d2.controller;

import com.workintech.s18d2.entity.Vegetable;
import com.workintech.s18d2.services.VegetableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workintech/vegetables")
@Slf4j
public class VegetableController {

    private VegetableService vegetableService;

    @Autowired
    public VegetableController(VegetableService vegetableService) {
        this.vegetableService = vegetableService;
    }

    @GetMapping
    public List<Vegetable> getVegetables() {
        return vegetableService.getByPriceAsc();
    }

    @GetMapping("/desc")
    public List<Vegetable> getVegetablesDesc() {
        return vegetableService.getByPriceDesc();
    }

    @GetMapping("/{id}")
    public Vegetable getVegetableById(@PathVariable Long id) {
        return vegetableService.getById(id);
    }

    @PostMapping("/{name}")
    public List<Vegetable> getVegetablesByName(@PathVariable String name) {
        return vegetableService.searchByName(name);
    }

    @PostMapping
    public Vegetable saveVegetable(@RequestBody Vegetable vegetable) {
        return vegetableService.save(vegetable);
    }

    @DeleteMapping("/{id}")
    public Vegetable deleteVegetable(@PathVariable Long id) {
        return vegetableService.delete(id);
    }
}
