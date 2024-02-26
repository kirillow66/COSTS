package ru.sberbank.jd.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import ru.sberbank.jd.controller.in.CategoryInput;
import ru.sberbank.jd.controller.in.CategoryUpdate;
import ru.sberbank.jd.entity.Category;
import ru.sberbank.jd.service.CategoryService;

/**
 * The type Category controller.
 */
@Controller
@RequestMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    /**
     * Create category.
     *
     * @param update the update
     * @param model  the model
     * @return the string
     */
    @PostMapping("/new")
    public String create(@ModelAttribute CategoryUpdate update, Model model) {

        CategoryInput input = new CategoryInput(update.name());
        log.info("[create] input={}", input);
        service.create(input);

        model.addAttribute("categories", service.get());
        return "categories";
    }

    /**
     * Update category.
     *
     * @param id     the id
     * @param update the update
     * @param model  the model
     * @return the string
     */
    @PostMapping("/{id}")
    public String update(@PathVariable("id") UUID id, @ModelAttribute CategoryUpdate update, Model model) {

        log.info("[update] update={}", update);
        service.update(new CategoryUpdate(id, update.name()));
        model.addAttribute("categories", service.get());
        return "categories";
    }

    /**
     * Delete category.
     *
     * @param update the update
     * @param model  the model
     * @return the string
     */
    @PostMapping("/delete")
    public String delete(@ModelAttribute CategoryUpdate update, Model model) {

        log.info("[delete] id={}", update.id());

        Category entity = service.delete(update.id());

        if (entity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }

        model.addAttribute("categories", service.get());
        return "categories";
    }

    /**
     * Get category.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/{id}")
    public String get(@PathVariable("id") UUID id, Model model) {

        log.info("[get] id={}", id);

        Category entity = service.get(id);

        if (entity != null) {
            model.addAttribute("id", entity.getId());
            model.addAttribute("name", entity.getName());
        }

        return "category";
    }

    /**
     * Get category.
     *
     * @return the string
     */
    @GetMapping("/new")
    public String get() {

        return "category";
    }

    /**
     * Get category.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping(value = {"", "/", "/all"})
    public String get(Model model) {

        log.info("[get all]");

        var categories = service.get();

        log.info("categories = {}", categories);

        model.addAttribute("categories", categories);
        return "categories";
    }
}
