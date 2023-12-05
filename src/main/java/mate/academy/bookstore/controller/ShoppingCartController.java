package mate.academy.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.cart.item.AddCartItemRequestDto;
import mate.academy.bookstore.dto.shopping.cart.ShoppingCartDto;
import mate.academy.bookstore.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping cart management", description = "Endpoints for managing shopping carts")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Get shopping cart", description = "Get shopping cart of authorized user")
    public ShoppingCartDto getShoppingCart() {
        return shoppingCartService.getShoppingCart();
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Add book to shopping cart",
            description = "Adding new book to shopping cart")
    public ShoppingCartDto addBookToShoppingCart(
            @RequestBody @Valid AddCartItemRequestDto requestDto) {
        return shoppingCartService.addBookToShoppingCart(requestDto.getBookId(),
                requestDto.getQuantity());
    }

    @PutMapping("/cart-items/{cartItemId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Update book quantity in shopping cart",
            description = "Updating book quantity in shopping cart item")
    public void updateBookQuantity(@PathVariable Long cartItemId,
                                   @RequestBody @Valid AddCartItemRequestDto requestDto) {
        shoppingCartService.updateBookQuantityInCartItem(cartItemId, requestDto.getQuantity());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/cart-items/{cartItemId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Delete book from shopping cart",
            description = "Deleting book from shopping cart")
    public void deleteBookFromShoppingCart(@PathVariable Long cartItemId) {
        shoppingCartService.removeBookFromShoppingCart(cartItemId);
    }
}
