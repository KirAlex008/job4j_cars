package ru.job4j.cars.storage;

import ru.job4j.cars.models.User;
import java.util.Collection;

public class Tester {
    public static void main(String[] args) {
        Store store = AdRepostiroty.getInstance();
        User user1 = store.findUser(2);
        Collection result = store.findLastDay(user1);
        System.out.println("findLastDay");
        result.forEach(System.out::println);
        result = store.findPhoto(user1);
        System.out.println("findPhoto");
        result.forEach(System.out::println);
        String brand = "Vaz";
        result = store.findBrand(user1,  brand);
        System.out.println("findBrand");
        result.forEach(System.out::println);
    }
}
