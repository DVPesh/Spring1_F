package ru.peshekhonov.solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    private LaptopRepository repository;

    @Autowired
    public void setLaptopRepository(LaptopRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/laptops/filter/price/less")
    public List<Laptop> filterByPriceLess(@RequestParam(required = false) Integer price) {
        if (price != null) {
            return repository.getAll().stream().filter(item -> item.getPrice() <= price).toList();
        } else {
            return repository.getAll();
        }
    }

    @GetMapping("/laptops/filter/price/more")
    public List<Laptop> filterByPriceMore(@RequestParam(required = false) Integer price) {
        if (price != null) {
            return repository.getAll().stream().filter(item -> item.getPrice() >= price).toList();
        } else {
            return repository.getAll();
        }
    }

    @GetMapping("/laptop/{id}")
    public String getById(@PathVariable int id) {
        Optional<Laptop> laptop = repository.getAll().stream().filter(item -> item.getId() == id).findFirst();
        return laptop.isPresent() ? String.format("Laptop: %s, price: %s", laptop.get().getTitle(), laptop.get().getPrice()) : "No laptop with id = " + id;
    }

    @GetMapping("/laptops")
    public List<Laptop> showAll() {
        return repository.getAll();
    }

    @PostMapping("/laptop/add")
    public void add(@RequestBody Laptop laptop) {
        int id = repository.getAll().stream().mapToInt(Laptop::getId).max().orElse(0) + 1;
        repository.add(new Laptop(id, laptop.getTitle(), laptop.getPrice()));
    }

    @DeleteMapping("/laptops")
    public void deleteAll() {
        repository.removeAll();
    }

    @DeleteMapping("/laptop/{id}")
    public void deleteById(@PathVariable int id) {
        repository.removeById(id);
    }
}
