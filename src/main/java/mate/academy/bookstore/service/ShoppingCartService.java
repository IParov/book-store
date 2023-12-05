package mate.academy.bookstore.service;

import mate.academy.bookstore.dto.shopping.cart.ShoppingCartDto;
import mate.academy.bookstore.model.User;

public interface ShoppingCartService {
    void addShoppingCart(User user);

    ShoppingCartDto getShoppingCart();

    ShoppingCartDto addBookToShoppingCart(Long bookId, int quantity);

    void removeBookFromShoppingCart(Long cartItemId);

    void updateBookQuantityInCartItem(Long cartItemId, int quantity);
}
