package ru.job4j.stream;

import java.util.List;
import java.util.stream.Collectors;

public class ProductLabel {
    public List<String> generateLabels(List<Product> products) {
        return products.stream()
                .filter(p -> p.getStandard() - p.getActual() >= 0)
                .filter(p -> p.getStandard() - p.getActual() <= 3)
                .map(p -> new Label(p.getName(), p.getPrice() / 2).toString())
                .collect(Collectors.toList());
    }
}
