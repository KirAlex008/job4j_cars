package ru.job4j.cars.storage;

import java.util.Collection;

public class Tester {
    public static void main(String[] args) {
        Store store = AdRepostiroty.getInstance();
        Collection result = store.findLastDay();
        System.out.println("findLastDay");
        result.forEach(System.out::println);
        result = store.findPhoto();
        System.out.println("findPhoto");
        result.forEach(System.out::println);
        String brand = "Vaz";
        result = store.findBrand(brand);
        System.out.println("findBrand");
        result.forEach(System.out::println);
    }
}
