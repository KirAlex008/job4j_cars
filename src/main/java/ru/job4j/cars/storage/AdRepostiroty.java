package ru.job4j.cars.storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.models.Advertisement;
import ru.job4j.cars.models.User;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class AdRepostiroty implements Store, AutoCloseable {

    private static final AdRepostiroty INSTANCE = new AdRepostiroty();

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private AdRepostiroty() {
    }

    public static AdRepostiroty getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final AdRepostiroty INSTANCE = new AdRepostiroty();
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    // объявления за последний день
    @Override
    public Collection<Advertisement> findLastDay(User user) {
        return this.tx(
                session -> session.createQuery("from Advertisement a where a.id=:user_id and date(current_date) - date(a.created) <= 1")
                        .setParameter("user_id", user.getId())
                        .list()
        );
    }

    // объявления с фото
    @Override
    public Collection<Advertisement> findPhoto(User user) {
        return this.tx(
                session -> session.createQuery("from Advertisement a where a.id=:user_id and a.photo!=null")
                        .setParameter("user_id", user.getId())
                        .list()
        );
    }
    // объявления определенной марки.
    @Override
    public Collection<Advertisement> findBrand(User user, String brand) {
        return this.tx(
                session -> session.createQuery("from Advertisement a where a.id=:user_id and a.brand=:brand")
                        .setParameter("user_id", user.getId())
                        .setParameter("brand", brand)
                        .list()
        );
    }
    @Override
    public User findUser(Integer id) {
        return this.tx(
                session -> (User) session.createQuery("from User u where u.id=:user_id").setParameter("user_id", 2).uniqueResult()
        );
    }


    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
