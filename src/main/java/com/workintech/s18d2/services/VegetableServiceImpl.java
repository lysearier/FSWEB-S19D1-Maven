package com.workintech.s18d2.services;

import com.workintech.s18d2.entity.Vegetable;
import com.workintech.s18d2.exceptions.PlantException;
import com.workintech.s18d2.repository.VegetableRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class VegetableServiceImpl implements VegetableService {

    private VegetableRepository vegetableRepository;

    @Autowired
    public VegetableServiceImpl(VegetableRepository vegetableRepository) {
        this.vegetableRepository = vegetableRepository;
    }

    @Override
    public List<Vegetable> getByPriceAsc() {
        return vegetableRepository.getByPriceAsc();
    }

    @Override
    public List<Vegetable> getByPriceDesc() {
        return vegetableRepository.getByPriceDesc();
    }

    @Override
    public Vegetable getById(Long id) {
        if (id <= 0) {
            throw new PlantException("Id must be greater than 0", HttpStatus.BAD_REQUEST);
        }

        Optional<Vegetable> vegetableOptional = vegetableRepository.findById(id);
        if (vegetableOptional.isPresent()) {
            return vegetableOptional.get();
        }

        throw new PlantException("Vegetable not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public Vegetable save(Vegetable vegetable) {
        if (vegetable.getName() == null || vegetable.getName().trim().isEmpty()) {
            throw new PlantException("Vegetable name cannot be null or empty", HttpStatus.BAD_REQUEST);
        }
        if (vegetable.getPrice() == null || vegetable.getPrice() < 0) {
            throw new PlantException("Vegetable price cannot be null or negative", HttpStatus.BAD_REQUEST);
        }

        return vegetableRepository.save(vegetable);
    }

    @Override
    public Vegetable delete(Long id) {
        Vegetable vegetable = getById(id);
        vegetableRepository.delete(vegetable);
        return vegetable;
    }

    @Override
    public List<Vegetable> searchByName(String name) {
        return vegetableRepository.searchByName(name);
    }
}
