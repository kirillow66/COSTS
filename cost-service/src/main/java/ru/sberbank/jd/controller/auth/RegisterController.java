package ru.sberbank.jd.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.sberbank.jd.dto.UserDto;
import ru.sberbank.jd.entity.User;
import ru.sberbank.jd.exception.UserFound;
import ru.sberbank.jd.mappers.UserMapper;
import ru.sberbank.jd.service.UserService;
import ru.sberbank.jd.service.security.AuthorizerUserService;

/**
 * The type Register controller.
 */
@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final AuthorizerUserService authorizerUserService;
    private final UserService userService;
    private final UserMapper userMapper;
    private String errorMessage = "";

    /**
     * Register string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping(value = {"", "/registerUser"})
    public String register(Model model) {
        if (authorizerUserService.isLogged()) {
            return "redirect:/";
        }

        model.addAttribute("registerForm", new UserDto());
        model.addAttribute("isLogged", authorizerUserService.isLogged());
        setErrorMessage(model);

        return "auth/registerForm";
    }

    /**
     * Create string.
     *
     * @param model the model
     * @param dto   the dto
     * @return the string
     */
    @PostMapping
    public String create(Model model, UserDto dto) {
        if (authorizerUserService.isLogged()) {
            return "redirect:/";
        }

        User user = userMapper.toEntity(dto);
        try {
            userService.create(user);
        } catch (UserFound e) {
            dto.setLogin("");
            dto.setPassword("");
            model.addAttribute("registerForm", dto);
            model.addAttribute("isLogged", authorizerUserService.isLogged());
            errorMessage = e.getMessage();
            setErrorMessage(model);
            return "auth/registerForm";
        }

        model.addAttribute("isLogged", authorizerUserService.isLogged());

        return "redirect:/login";
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
