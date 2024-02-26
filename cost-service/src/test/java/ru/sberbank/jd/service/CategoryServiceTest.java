package ru.sberbank.jd.service;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.sberbank.jd.controller.in.CategoryInput;
import ru.sberbank.jd.controller.in.CategoryUpdate;
import ru.sberbank.jd.entity.Category;
import ru.sberbank.jd.repository.CategoryRepository;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Import(TestConfig.class)
@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @MockBean
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    @Test
    void create() {

        CategoryInput input = new CategoryInput("Досуг");
        Category category = Category.of(input);

        Mockito.when(categoryRepository.save(category)).thenReturn(category);
        Category test = categoryService.create(input);

        Assertions.assertEquals(category.getName(), test.getName());
    }

    @Test
    void getById() {

        UUID categoryId = UUID.randomUUID();
        Category category = Category.of(new CategoryUpdate(categoryId, "Досуг"));

        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        Category test = categoryService.get(categoryId);

        Assertions.assertEquals(category, test);
    }
}