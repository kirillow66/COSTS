package ru.sberbank.jd.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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

@Controller
@RequestMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    public String update(@ModelAttribute CategoryUpdate update, Model model) {

        if (update.id() == null) {

            CategoryInput input = new CategoryInput(update.name());
            log.info("[create] input={}", input);
            Category newEntity = service.create(input);

        } else {

            log.info("[update] update={}", update);
            Category updatedEntity = service.update(update);
        }

        model.addAttribute("categories", service.get());
        return "categories";
    }

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

    @GetMapping("/new")
    public String get() {

        return "category";
    }

    @GetMapping(value = {"", "/", "/all"})
    public String get(HttpServletRequest request, Model model) {

        log.info("[get all]");

        var categories = service.get();

        log.info("categories = {}", categories);

        model.addAttribute("categories", categories);
        return "categories";
    }
}
