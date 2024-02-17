package ru.sberbank.jd.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.controller.in.CategoryInput;
import ru.sberbank.jd.controller.in.CategoryUpdate;
import ru.sberbank.jd.entity.Category;
import ru.sberbank.jd.repository.CategoryRepository;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public Category create(@NonNull CategoryInput input) {

        Category entity = Category.of(input);
        repository.save(entity);
        return entity;
    }

    public Category get(UUID id) {

        Optional<Category> entity = repository.findById(id);
        return entity.orElse(null);
    }

    public List<Category> get() {

        return repository.findAll();
    }

    public Category update(@NonNull CategoryUpdate update) {

        Category entity = null;

        if (repository.findById(update.id()).orElse(null) != null) {

            entity = Category.of(update);
            repository.save(entity);
        }

        return entity;
    }

    public Category delete(UUID id) {

        Category entity = repository.findById(id).orElse(null);

        if (entity != null) {
            repository.delete(entity);
        }

        return entity;
    }

}
