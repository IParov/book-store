package mate.academy.bookstore.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.repository.AbstractRepository;
import mate.academy.bookstore.repository.BookRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl extends AbstractRepository<Book> implements BookRepository {
    public BookRepositoryImpl(SessionFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    public List<Book> findAll() {
        try (Session session = entityManagerFactory.openSession()) {
            CriteriaQuery<Book> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Book.class);
            criteriaQuery.from(Book.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new EntityNotFoundException("Can't get all books", e);
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Book book = entityManager.find(Book.class, id);
            return Optional.ofNullable(book);
        } catch (Exception e) {
            throw new EntityNotFoundException("Can't get book by id: " + id, e);
        }
    }
}
