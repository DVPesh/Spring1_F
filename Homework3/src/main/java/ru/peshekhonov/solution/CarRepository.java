package ru.peshekhonov.solution;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CarRepository {

    private List<Car> cars;

    @PostConstruct
    public void init() {
        this.cars = new ArrayList<>();
        this.cars.add(new Car(1, "Chery Tiggo 4", 750000));
        this.cars.add(new Car(2, "Chery Tiggo 7 Pro", 820000));
        this.cars.add(new Car(3, "Chery Tiggo 8 Pro", 870000));
    }

    public List<Car> getAll() {
        return Collections.unmodifiableList(cars);
    }

    public void add(Car car) {
        cars.add(car);
    }

    public void removeById(int id) {
        cars.removeIf(item -> item.getId() == id);
    }

    public void removeAll() {
        cars.clear();
    }
}
