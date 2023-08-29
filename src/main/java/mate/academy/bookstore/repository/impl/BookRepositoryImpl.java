package mate.academy.bookstore.repository.impl;

import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;
import mate.academy.bookstore.exception.DataProcessingException;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.repository.AbstractRepository;
import mate.academy.bookstore.repository.BookRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl extends AbstractRepository<Book> implements BookRepository {
    public BookRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Book> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Book> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Book.class);
            criteriaQuery.from(Book.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all books", e);
        }
    }
}
