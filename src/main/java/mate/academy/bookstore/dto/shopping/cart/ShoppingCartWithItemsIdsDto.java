package mate.academy.bookstore.dto.shopping.cart;

import java.util.Set;

public record ShoppingCartWithItemsIdsDto(Long id, Long userId, Set<Long> itemsIds) {
}
