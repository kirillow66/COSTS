package ru.sberbank.jd.controller;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.sberbank.jd.controller.in.CategoryInput;
import ru.sberbank.jd.controller.in.CategoryUpdate;
import ru.sberbank.jd.entity.Category;
import ru.sberbank.jd.service.CategoryService;

/**
 * The type Category rest controller.
 */
@RestController
@RequestMapping(value = "/api/categories", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryService service;

    /**
     * Create category.
     *
     * @param input the input
     * @return the category
     */
    @PostMapping
    public Category create(@RequestBody CategoryInput input) {

        log.info("[create] input={}", input);
        return service.create(input);
    }

    /**
     * Get category.
     *
     * @param id the id
     * @return the category
     */
    @GetMapping("/{id}")
    public Category get(@PathVariable("id") UUID id) {

        log.info("[get] id={}", id);

        Category entity = service.get(id);

        if (entity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        } else {
            return entity;
        }
    }

    /**
     * Get list.
     *
     * @return the list
     */
    @GetMapping(value={"", "/"})
    public List<Category> get() {

        log.info("[get all]");
        return service.get();
    }

    /**
     * Update category.
     *
     * @param update the update
     * @return the category
     */
    @PutMapping
    public Category update(@RequestBody CategoryUpdate update) {

        log.info("[update] update={}", update);
        Category entity = service.update(update);

        if (entity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        } else {
            return entity;
        }
    }

    /**
     * Delete category.
     *
     * @param id the id
     * @return the category
     */
    @DeleteMapping("/{id}")
    public Category delete(@PathVariable("id") UUID id) {

        log.info("[delete] id={}", id);

        Category entity = service.delete(id);

        if (entity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        } else {
            return entity;
        }
    }
}
