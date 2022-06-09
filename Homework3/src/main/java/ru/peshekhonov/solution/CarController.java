package ru.peshekhonov.solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CarController {

    private CarRepository repository;

    @Autowired
    public void setCarRepository(CarRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cars/filter/price/less")
    @ResponseBody
    public List<Car> filterByPriceLess(@RequestParam(required = false) Integer price) {
        if (price != null) {
            return repository.getAll().stream().filter(item -> item.getPrice() <= price).toList();
        } else {
            return repository.getAll();
        }
    }

    @GetMapping("/cars/filter/price/more")
    @ResponseBody
    public List<Car> filterByPriceMore(@RequestParam(required = false) Integer price) {
        if (price != null) {
            return repository.getAll().stream().filter(item -> item.getPrice() >= price).toList();
        } else {
            return repository.getAll();
        }
    }

    @GetMapping("/car/{id}")
    @ResponseBody
    public String getById(@PathVariable int id) {
        Optional<Car> car = repository.getAll().stream().filter(item -> item.getId() == id).findFirst();
        return car.isPresent() ? String.format("Car: %s, price: %s", car.get().getTitle(), car.get().getPrice()) : "No car with id = " + id;
    }

    @GetMapping("/cars")
    public String showAll(Model model) {
        List<Car> cars = repository.getAll();
        model.addAttribute("cars", cars);
        return "list";
    }

    @PostMapping("/car/add")
    @ResponseBody
    public void add(@RequestBody Car car) {
        int id = repository.getAll().stream().mapToInt(Car::getId).max().orElse(0) + 1;
        repository.add(new Car(id, car.getTitle(), car.getPrice()));
    }

    @DeleteMapping("/cars")
    @ResponseBody
    public void deleteAll() {
        repository.removeAll();
    }

    @DeleteMapping("/car/{id}")
    @ResponseBody
    public void deleteById(@PathVariable int id) {
        repository.removeById(id);
    }
}
