package mate.academy.bookstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "categories")
@SQLDelete(sql = "UPDATE categories SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String description;
    @Column(nullable = false)
    @Getter
    @Setter
    private boolean isDeleted = false;
}
