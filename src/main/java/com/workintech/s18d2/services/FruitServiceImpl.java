package com.workintech.s18d2.services;

import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.exceptions.PlantException;
import com.workintech.s18d2.repository.FruitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FruitServiceImpl implements FruitService {

    private FruitRepository fruitRepository;

    public FruitServiceImpl(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @Override
    public List<Fruit> getByPriceAsc() {
        return fruitRepository.getByPriceAsc();
    }

    @Override
    public List<Fruit> getByPriceDesc() {
        return fruitRepository.getByPriceDesc();
    }

    @Override
    public Fruit getById(Long id) {
        if (id <= 0) {
            throw new PlantException("Id must be greater than 0", HttpStatus.BAD_REQUEST);
        }

        Optional<Fruit> fruitOptional = fruitRepository.findById(id);
        if (fruitOptional.isPresent()) {
            return fruitOptional.get();
        }

        throw new PlantException("Fruit not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public Fruit save(Fruit fruit) {
        if (fruit == null) {
            throw new PlantException("Fruit cannot be null", HttpStatus.BAD_REQUEST);
        }

        if (fruit.getName() != null && fruit.getName().trim().isEmpty()) {
            throw new PlantException("Fruit name cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (fruit.getPrice() != null && fruit.getPrice() < 0) {
            throw new PlantException("Fruit price cannot be negative", HttpStatus.BAD_REQUEST);
        }

        return fruitRepository.save(fruit);
    }

    @Override
    public Fruit delete(Long id) {
        Fruit fruit = getById(id);
        fruitRepository.delete(fruit);
        return fruit;
    }

    @Override
    public List<Fruit> searchByName(String name) {
        return fruitRepository.searchByName(name);
    }
}
