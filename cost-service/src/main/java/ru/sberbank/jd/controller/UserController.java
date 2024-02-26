package ru.sberbank.jd.controller;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.sberbank.jd.dto.UserDto;
import ru.sberbank.jd.entity.User;
import ru.sberbank.jd.exception.UserCantDeleted;
import ru.sberbank.jd.exception.UserFound;
import ru.sberbank.jd.exception.UserNotFound;
import ru.sberbank.jd.mappers.UserMapper;
import ru.sberbank.jd.service.UserService;

/**
 * The type User controller.
 */
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private String errorMessage = "";

    /**
     * Find all string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping(value = {"/all", "/"})
    public String findAll(Model model) {
        log.info("[findAll]");
        List<UserDto> users = userMapper.toDto(userService.findAll());
        log.info("[findAll] output={}", users);

        model.addAttribute("users", users);
        setErrorMessage(model);

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
        setErrorMessage(model);

        return "userCreate";
    }

    /**
     * Create string.
     *
     * @param dto   the dto
     * @param model the model
     * @return the string
     */
    @PostMapping("/userCreate")
    public String create(@ModelAttribute UserDto dto, Model model) {

        User user = userMapper.toEntity(dto);
        log.info("[create] input={}", user.toString());
        try {
            userService.create(user);
        } catch (UserFound e) {
            dto.setLogin("");
            dto.setPassword("");
            model.addAttribute("registerForm", dto);
            errorMessage = e.getMessage();
            setErrorMessage(model);
            return "userCreate";
        }
        return "redirect:/users/all";
    }

    /**
     * User delete string.
     *
     * @param dto   the dto
     * @param model the model
     * @return the string
     */
    @PostMapping("/userDelete")
    @PreAuthorize("hasRole('ADMIN')")
    public String userDelete(@ModelAttribute UserDto dto, Model model) {

        log.info("[delete] userId={}", dto.getId());
        try {
            userService.delete(dto.getId());
        } catch (UserCantDeleted | UserNotFound e) {
            errorMessage = e.getMessage();
        }

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

    private void setErrorMessage(Model model) {
        if (!errorMessage.isEmpty()) {
            model.addAttribute("errorMessage", errorMessage);
            errorMessage = "";
        }
    }
}
