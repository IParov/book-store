package mate.academy.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.shopping.cart.ShoppingCartDto;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.mapper.ShoppingCartMapper;
import mate.academy.bookstore.model.CartItem;
import mate.academy.bookstore.model.ShoppingCart;
import mate.academy.bookstore.model.User;
import mate.academy.bookstore.repository.book.BookRepository;
import mate.academy.bookstore.repository.cart.item.CartItemRepository;
import mate.academy.bookstore.repository.shopping.cart.ShoppingCartRepository;
import mate.academy.bookstore.repository.user.UserRepository;
import mate.academy.bookstore.service.ShoppingCartService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;

    @Override
    public void addShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCartDto getShoppingCart() {
        return shoppingCartMapper.toDto(getAuthorizedUserShoppingCart());
    }

    @Override
    @Transactional
    public ShoppingCartDto addBookToShoppingCart(Long bookId, int quantity) {
        ShoppingCart shoppingCart = getAuthorizedUserShoppingCart();
        CartItem cartItem = new CartItem();
        cartItem.setBook(bookRepository.findById(bookId).orElseThrow(
                () -> new EntityNotFoundException("Can't find book by Id" + bookId)
        ));
        cartItem.setQuantity(quantity);
        cartItem.setShoppingCart(shoppingCart);
        shoppingCart.getCartItems().add(cartItem);
        return shoppingCartMapper.toDto(shoppingCartRepository.save(shoppingCart));
    }

    @Override
    @Transactional
    public void removeBookFromShoppingCart(Long cartItemId) {
        ShoppingCart shoppingCart = getAuthorizedUserShoppingCart();
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(
                () -> new EntityNotFoundException("Can't find cart item by book id " + cartItemId)
        );
        shoppingCart.removeCartItem(cartItem);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void updateBookQuantityInCartItem(Long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(
                () -> new EntityNotFoundException("Can't find cart item by Id " + cartItemId)
        );
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }

    private ShoppingCart getAuthorizedUserShoppingCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(
                () -> new RuntimeException("Can't find authorized user"));
        return shoppingCartRepository.getByUserId(user.getId()).orElseThrow(
                () -> new EntityNotFoundException("Can't find shopping cart by user Id "
                        + user.getId())
        );
    }
}
