package mate.academy.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.book.BookDtoWithoutCategoryIds;
import mate.academy.bookstore.dto.category.CategoryDto;
import mate.academy.bookstore.dto.category.CreateCategoryRequestDto;
import mate.academy.bookstore.service.BookService;
import mate.academy.bookstore.service.CategoryService;
import org.springframework.data.domain.Pageable;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
@Tag(name = "Category management", description = "Endpoints for managing categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Create a new category", description = "Create a new category")
    public CategoryDto createCategory(@RequestBody @Valid CreateCategoryRequestDto requestDto) {
        return categoryService.save(requestDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Update category", description = "Update existing category")
    public CategoryDto updateCategory(@PathVariable Long id,
                                      @RequestBody @Valid CreateCategoryRequestDto requestDto) {
        return categoryService.update(id, requestDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Get category", description = "Get category by Id")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Get all categories", description = "Get all categories")
    public List<CategoryDto> findAll(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Delete category", description = "Delete category by Id")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @GetMapping("/{id}/books")
    @Operation(summary = "Find books by category", description = "Find books by category id")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<BookDtoWithoutCategoryIds> getBooksByCategoryId(@PathVariable Long id) {
        return bookService.findAllByCategory(id);
    }
}
