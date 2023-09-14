package mate.academy.bookstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "books")
@SQLDelete(sql = "UPDATE books SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
    @Column(nullable = false)
    @Getter
    @Setter
    private String title;
    @Column(nullable = false)
    @Getter
    @Setter
    private String author;
    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String isbn;
    @Column(nullable = false)
    @Getter
    @Setter
    private BigDecimal price;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private String coverImage;
    @Column(nullable = false)
    @Getter
    @Setter
    private boolean isDeleted = false;
}
