package ru.job4j.cars.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private String brand;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<CarBody> bodies = new ArrayList<>();
    private String photo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    private boolean sold;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Advertisement() {
    }

    public Advertisement(Integer id, String description, String brand, String photo, Date created, boolean sold, User user) {
        this.id = id;
        this.description = description;
        this.brand = brand;
        this.photo = photo;
        this.created = created;
        this.sold = sold;
        this.user = user;
    }

    public List<CarBody> getBodies() {
        return bodies;
    }

    public void addCarBody(CarBody body) {
        this.bodies.add(body);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        Advertisement that = (Advertisement) o;
        return Objects.equals(id, that.id);
    }
}
