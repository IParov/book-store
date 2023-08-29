package mate.academy.bookstore.mapper;

import mate.academy.bookstore.dto.book.BookDto;
import mate.academy.bookstore.model.Book;

public interface BookMapper {
    BookDto toDto(Book book);
}
