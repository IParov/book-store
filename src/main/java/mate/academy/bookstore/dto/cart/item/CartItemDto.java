package mate.academy.bookstore.dto.cart.item;

public record CartItemDto(Long id, Long bookId, String bookTitle, int quantity) {
}
