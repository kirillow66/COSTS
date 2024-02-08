package ru.sberbank.jd.controller;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.sberbank.jd.dto.UserDto;
import ru.sberbank.jd.entity.User;
import ru.sberbank.jd.mappers.UserMapper;
import ru.sberbank.jd.service.UserService;

/**
 * The type User controller.
 */
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * Find all string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/all")
    public String findAll(Model model) {
        List<UserDto> users = userMapper.toDto(userService.findAll());
        model.addAttribute("users", users);
        return "users";
    }

    /**
     * Create form string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/userCreate")
    public String createForm(Model model) {
        model.addAttribute("registerForm", new UserDto());
        return "userCreate";
    }

    /**
     * Create string.
     *
     * @param model the model
     * @param dto   the dto
     * @return the string
     */
    @PostMapping("/userCreate")
    public String create(Model model, UserDto dto) {

        User user = userMapper.toEntity(dto);
        userService.create(user);

        return "redirect:/users/all";
    }

    /**
     * User delete string.
     *
     * @param model  the model
     * @param userId the user id
     * @return the string
     */
    @DeleteMapping("/{userId}")
    public String userDelete(Model model, @PathVariable(name = "userId") UUID userId) {

        userService.delete(userId);

        return "redirect:/users/all";
    }

    /**
     * Handle string.
     *
     * @return the string
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public String handle() {
        return "error/500";
    }

}
