package mate.academy.bookstore.dto.shopping.cart;

import java.util.Set;
import lombok.Data;
import mate.academy.bookstore.dto.cart.item.CartItemDto;

@Data
public class ShoppingCartDto {
    private Long id;
    private Long userId;
    private Set<CartItemDto> cartItems;
}
