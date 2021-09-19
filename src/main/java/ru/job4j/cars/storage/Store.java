package ru.job4j.cars.storage;

import ru.job4j.cars.models.Advertisement;
import ru.job4j.cars.models.User;

import javax.xml.crypto.Data;
import java.util.Collection;

public interface Store {

    public Collection<Advertisement> findLastDay(User user);

    public Collection<Advertisement> findPhoto(User user);

    public Collection<Advertisement> findBrand(User user, String brand);

    public User findUser(Integer id);

}
