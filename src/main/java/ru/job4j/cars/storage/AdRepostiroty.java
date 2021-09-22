package ru.job4j.cars.storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.models.Advertisement;
import java.util.Collection;
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
    public Collection<Advertisement> findLastDay() {
        return this.tx(
                session -> session.createQuery("from Advertisement a where a.created > current_date - 1")
                        .list()
        );
    }

    // объявления с фото
    @Override
    public Collection<Advertisement> findPhoto() {
        return this.tx(
                session -> session.createQuery("from Advertisement a where a.photo!=null")
                        .list()
        );
    }
    // объявления определенной марки.
    @Override
    public Collection<Advertisement> findBrand( String brand) {
        return this.tx(
                session -> session.createQuery("from Advertisement a where a.brand=:brand")
                        .setParameter("brand", brand)
                        .list()
        );
    }

    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
