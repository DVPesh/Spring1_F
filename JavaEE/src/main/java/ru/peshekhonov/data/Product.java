package ru.peshekhonov.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Product {

    private int id;
    private String title;
    private int cost;

    @Override
    public String toString() {
        return String.format("%-24s %4s Rubles [id = %2s]", (title + ":"), cost, id);
    }
}
