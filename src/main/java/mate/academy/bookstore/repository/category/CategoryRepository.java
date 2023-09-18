package mate.academy.bookstore.repository.category;

import java.util.Set;
import mate.academy.bookstore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Set<Category> findAllByIdIn(Set<Long> ids);
}
