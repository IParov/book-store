package mate.academy.bookstore.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.book.BookDto;
import mate.academy.bookstore.dto.book.CreateBookRequestDto;
import mate.academy.bookstore.mapper.BookMapper;
import mate.academy.bookstore.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookController {
private final BookService bookService;
private final BookMapper bookMapper;

@PostMapping
    public BookDto save(@RequestBody CreateBookRequestDto requestDto) {
    return bookService.save(requestDto);
}

@GetMapping
    public List<BookDto> findAll() {
    return bookService.findAll();
}

@GetMapping("/{id}")
    public BookDto findById(@PathVariable Long id) {
    return bookService.findById(id);
}
}
