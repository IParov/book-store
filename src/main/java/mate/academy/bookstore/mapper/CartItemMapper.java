package mate.academy.bookstore.mapper;

import java.util.Optional;
import mate.academy.bookstore.config.MapperConfig;
import mate.academy.bookstore.dto.cart.item.CartItemDto;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    @Mapping(target = "bookId", source = "book.id")
    @Mapping(target = "bookTitle", source = "book.title")
    CartItemDto toDto(CartItem cartItem);

    @Mapping(target = "book", source = "bookId", qualifiedByName = "bookFromId")
    CartItem toEntity(CartItemDto cartItemDto);

    @Named("bookFromId")
    default Book bookFromId(Long bookId) {
        return Optional.ofNullable(bookId)
                .map(Book::new)
                .orElse(null);
    }
}
