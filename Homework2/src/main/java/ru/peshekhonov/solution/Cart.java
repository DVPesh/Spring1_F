package ru.peshekhonov.solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@Scope("prototype")
public class Cart {

    private final ProductRepository productRepository;
    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = Collections.synchronizedList(new ArrayList<>());
    }

    @Autowired
    public Cart(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProductById(int id) {
        this.products.add(productRepository.getProductById(id).orElseThrow(() -> new NoSuchElementException("нет такого товара")));
    }

    public boolean removeProductById(int id) {
        synchronized (products) {
            return this.products.removeIf(p -> p.getId() == id);
        }
    }

    public void removeAll() {
        products.clear();
    }

    public List<Product> getProducts() {
        synchronized (products) {
            return List.copyOf(products);
        }
    }
}
