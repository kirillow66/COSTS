package ru.sberbank.jd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import ru.sberbank.jd.controller.in.CategoryInput;
import ru.sberbank.jd.controller.in.CategoryUpdate;

/**
 * The type Category.
 */
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String name;

    @ToString.Exclude
    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    public static Category of(CategoryInput input) {
        return new CategoryBuilder()
                .id(UUID.randomUUID())
                .name(input.name())
                .build();
    }

    public static Category of(CategoryUpdate update) {
        return new CategoryBuilder()
                .id(update.id())
                .name(update.name())
                .build();
    }
}
