package ru.sberbank.jd.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.controller.in.CategoryInput;
import ru.sberbank.jd.controller.in.CategoryUpdate;
import ru.sberbank.jd.entity.Category;
import ru.sberbank.jd.repository.CategoryRepository;

/**
 * The type Category service.
 */
@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    /**
     * Create category.
     *
     * @param input the input
     * @return the category
     */
    public Category create(@NonNull CategoryInput input) {

        Category entity = Category.of(input);
        repository.save(entity);
        return entity;
    }

    /**
     * Get category.
     *
     * @param id the id
     * @return the category
     */
    public Category get(UUID id) {

        Optional<Category> entity = repository.findById(id);
        return entity.orElse(null);
    }

    /**
     * Get list.
     *
     * @return the list
     */
    public List<Category> get() {

        return repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    /**
     * Update category.
     *
     * @param update the update
     * @return the category
     */
    public Category update(@NonNull CategoryUpdate update) {

        Category entity = null;

        if (repository.findById(update.id()).orElse(null) != null) {

            entity = Category.of(update);
            repository.save(entity);
        }

        return entity;
    }

    /**
     * Delete category.
     *
     * @param id the id
     * @return the category
     */
    public Category delete(UUID id) {

        Category entity = repository.findById(id).orElse(null);

        if (entity != null) {
            repository.delete(entity);
        }

        return entity;
    }

}
