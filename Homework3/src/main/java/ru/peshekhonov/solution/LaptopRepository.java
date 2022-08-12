package ru.peshekhonov.solution;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class LaptopRepository {

    private List<Laptop> laptops;

    @PostConstruct
    public void init() {
        this.laptops = new ArrayList<>();
        this.laptops.add(new Laptop(1, "Apple MacBook Air 13 Late 2020 MGN63LL/A", 86020));
        this.laptops.add(new Laptop(2, "HP Envy 15-ep1027ur 4Z2Q1EA", 231650));
        this.laptops.add(new Laptop(3, "ASUS Vivobook Pro 16 OLED M7600QC-L2003", 127585));
    }

    public List<Laptop> getAll() {
        return Collections.unmodifiableList(laptops);
    }

    public void add(Laptop laptop) {
        laptops.add(laptop);
    }

    public void removeById(int id) {
        laptops.removeIf(item -> item.getId() == id);
    }

    public void removeAll() {
        laptops.clear();
    }
}
