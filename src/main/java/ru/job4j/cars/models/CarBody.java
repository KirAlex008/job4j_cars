package ru.job4j.cars.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "car_body")

public class CarBody {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public CarBody() {
    }

    public CarBody(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarBody carBody = (CarBody) o;
        return Objects.equals(id, carBody.id);
    }
}
