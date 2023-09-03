package mate.academy.bookstore.repository.book.specification.provider;

import java.util.Arrays;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AuthorSpecificationProvider implements SpecificationProvider<Book> {
    private static final String ATTRIBUTE_NAME = "author";

    @Override
    public String getKey() {
        return ATTRIBUTE_NAME;
    }

    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root.get(ATTRIBUTE_NAME)
                .in(Arrays.stream(params).toArray());
    }
}
