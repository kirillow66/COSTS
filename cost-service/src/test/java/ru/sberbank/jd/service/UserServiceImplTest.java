package ru.sberbank.jd.service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.Mockito;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.sberbank.jd.entity.User;
import ru.sberbank.jd.entity.UserRole;
import ru.sberbank.jd.exception.UserFound;
import ru.sberbank.jd.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Import(TestConfig.class)
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private UserServiceImpl userService;
   
    @Test
    void findById() {
        User user = new User();
        UUID userId = UUID.randomUUID();
        user.setId(userId);
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        User testUser = userService.findById(userId);
        Mockito.verify(userRepository).findById(userId);
        Assertions.assertEquals(user,testUser);
    }

    @Test
    void create() {
        String username = "username@gmail.com";
        String password = "password";
        User user = new User();
        user.setLogin(username);
        user.setPassword(password);
        Mockito.when(userRepository.findByLogin(username))
                .thenReturn(Optional.empty());
        Mockito.when(passwordEncoder.encode(password))
                .thenReturn("encodedPassword");
        User testUser = userService.create(user);
        Mockito.verify(userRepository).save(user);
        Assertions.assertEquals(Set.of(UserRole.OWNER), testUser.getUserRoles());
        Assertions.assertEquals("encodedPassword",
                testUser.getPassword());
    }

    @Test
    void createWithExistingUsername() {
        String username = "username@gmail.com";
        String password = "password";
        User user = new User();
        user.setLogin(username);
        user.setPassword(password);
        Mockito.when(userRepository.findByLogin(username))
                .thenReturn(Optional.of(new User()));
        Mockito.when(passwordEncoder.encode(password))
                .thenReturn("encodedPassword");
        Assertions.assertThrows(UserFound.class,
                () -> userService.create(user));
        Mockito.verify(userRepository, Mockito.never()).save(user);
    }

    @Test
    void delete() {
        UUID userId = UUID.randomUUID();
        userService.delete(userId);
        Mockito.verify(userRepository).deleteById(userId);
    }
    
}