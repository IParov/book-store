package mate.academy.bookstore.repository;

import mate.academy.bookstore.exception.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractRepository<T> implements GenericRepository<T> {
    protected final SessionFactory entityManagerFactory;

    public AbstractRepository(SessionFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public T save(T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = entityManagerFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EntityNotFoundException("Can't insert " + entity.getClass().getSimpleName()
                    + ": " + entity, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
