package ru.sberbank.jd.service;

import lombok.RequiredArgsConstructor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.sberbank.jd.repository.CategoryRepository;
import ru.sberbank.jd.repository.CostRepository;
import ru.sberbank.jd.repository.UserRepository;

@TestConfiguration
@RequiredArgsConstructor
public class TestConfig {
    @Bean
    @Primary
    public BCryptPasswordEncoder testPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Primary
    public UserServiceImpl userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository, testPasswordEncoder());
    }

    @Bean
    public UserRepository userRepository() {
        return Mockito.mock(UserRepository.class);
    }

    @Bean
    public CategoryRepository categoryRepository() {
        return Mockito.mock(CategoryRepository.class);
    }

    @Bean
    @Primary
    public CategoryService categoryService(CategoryRepository categoryRepository) {

        return new CategoryService(categoryRepository);
    }

    @Bean
    public CostRepository costRepository() {
        return Mockito.mock(CostRepository.class);
    }
    @Bean
    @Primary
    public CostService costService(CostRepository costRepository) {
        return new CostService(costRepository);
    }
}
