package mate.academy.bookstore.dto.book;

public record BookSearchParametersDto(String[] title, String[] authors, String[] isbn,
                                      String[] price) {
}
