package ru.peshekhonov.solution;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Product {

    private int id;
    private String title;
    private int price;
}
