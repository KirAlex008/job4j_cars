package ru.job4j.cars.storage;

import ru.job4j.cars.models.Advertisement;
import ru.job4j.cars.models.User;

import javax.xml.crypto.Data;
import java.util.Collection;

public interface Store {

    public Collection<Advertisement> findLastDay();

    public Collection<Advertisement> findPhoto();

    public Collection<Advertisement> findBrand(String brand);

}
