package ru.peshekhonov.solution;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Application {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int currentCart = 0;
        List<Cart> carts = new ArrayList<>();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductRepository productRepository = context.getBean(ProductRepository.class);

        while (true) {
            System.out.println("0-новая корзина, 1-список продуктов");
            System.out.println("2-добавить продукт в корзину, 3-убрать продукт из корзины");
            System.out.println("4-очистить корзину, 5-выбрать корзину");
            switch (readValue()) {
                case 0:
                    carts.add(context.getBean(Cart.class));
                    currentCart = carts.size();
                    System.out.printf("Корзина №%s%n", currentCart);
                    break;
                case 1:
                    System.out.println(productRepository.getProducts());
                    break;
                case 2:
                    if (currentCart > 0) {
                        System.out.println("id продукта?");
                        try {
                            carts.get(currentCart - 1).addProductById(readValue());
                        } catch (NoSuchElementException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 3:
                    if (currentCart > 0) {
                        System.out.println("id продукта?");
                        carts.get(currentCart - 1).removeProductById(readValue());
                    }
                    break;
                case 4:
                    if (currentCart > 0) {
                        carts.get(currentCart - 1).removeAll();
                    }
                    break;
                case 5:
                    if (currentCart > 0) {
                        System.out.println("номер корзины?");
                        int value = readValue();
                        if (value <= carts.size() && value > 0) {
                            currentCart = value;
                        }
                    }
            }
            System.out.printf("количество корзин: %s, текущая корзина: %s%n", carts.size(), currentCart > 0 ? currentCart : "нет");
            if (currentCart > 0) {
                System.out.println("содержимое текущей корзины");
                System.out.println(carts.get(currentCart - 1).getProducts());
            }
        }
    }

    private static int readValue() {
        while (!scanner.hasNextInt()) {
            System.exit(0);
            scanner.next();
        }
        return scanner.nextInt();
    }
}
