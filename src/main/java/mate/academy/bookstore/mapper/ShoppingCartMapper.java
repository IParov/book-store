package mate.academy.bookstore.mapper;

import java.util.Optional;
import java.util.stream.Collectors;
import mate.academy.bookstore.config.MapperConfig;
import mate.academy.bookstore.dto.cart.item.CartItemDto;
import mate.academy.bookstore.dto.shopping.cart.ShoppingCartDto;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.model.CartItem;
import mate.academy.bookstore.model.ShoppingCart;
import mate.academy.bookstore.model.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface ShoppingCartMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "cartItems", ignore = true)
    ShoppingCartDto toDto(ShoppingCart shoppingCart);

    @Mapping(target = "user", source = "userId", qualifiedByName = "userById")
    @Mapping(target = "cartItems", ignore = true)
    ShoppingCart toEntity(ShoppingCartDto cartDto);

    @Mapping(target = "bookId", source = "book.id")
    @Mapping(target = "bookTitle", source = "book.title")
    CartItemDto cartItemToDto(CartItem cartItem);

    @Mapping(target = "book", source = "bookId", qualifiedByName = "bookFromId")
    CartItem cartItemDtoToEntity(CartItemDto cartItemDto);

    @AfterMapping
    default void setCartItemDtos(@MappingTarget ShoppingCartDto cartDto,
                                 ShoppingCart shoppingCart) {
        cartDto.setCartItems(shoppingCart.getCartItems().stream()
                .map(this::cartItemToDto)
                .collect(Collectors.toSet()));
    }

    @AfterMapping
    default void setCartItems(@MappingTarget ShoppingCart shoppingCart, ShoppingCartDto cartDto) {
        shoppingCart.setCartItems(cartDto.getCartItems()
                .stream()
                .map(this::cartItemDtoToEntity)
                .collect(Collectors.toSet()));
    }

    @Named("userById")
    default User userById(Long userId) {
        return Optional.ofNullable(userId)
                .map(User::new)
                .orElse(null);
    }

    @Named("bookFromId")
    default Book bookFromId(Long bookId) {
        return Optional.ofNullable(bookId)
                .map(Book::new)
                .orElse(null);
    }
}
