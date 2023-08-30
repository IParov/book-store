package mate.academy.bookstore;

import java.math.BigDecimal;
import mate.academy.bookstore.dto.book.CreateBookRequestDto;
import mate.academy.bookstore.mapper.BookMapper;
import mate.academy.bookstore.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStoreApplication {
    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookStoreApplication(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                CreateBookRequestDto book = new CreateBookRequestDto();
                book.setAuthor("Stephen King");
                book.setTitle("Misery");
                book.setDescription("Misery is an American psychological horror thriller novel "
                        + "written by Stephen King");
                book.setIsbn("A1B2C3");
                book.setPrice(BigDecimal.valueOf(25));
                book.setCoverImage("cover image link");
                bookService.save(book);
                System.out.println(bookService.findAll());
            }
        };
    }

}
