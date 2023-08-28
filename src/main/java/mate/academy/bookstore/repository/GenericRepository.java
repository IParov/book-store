package mate.academy.bookstore.repository;

public interface GenericRepository<T> {
    T save(T entity);
}
