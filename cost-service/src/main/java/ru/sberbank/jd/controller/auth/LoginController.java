package ru.sberbank.jd.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.sberbank.jd.service.security.AuthorizerUserService;

/**
 * The type Login controller.
 */
@Controller
@RequestMapping
@RequiredArgsConstructor
public class LoginController {
    private final AuthorizerUserService authorizerUserService;

    /**
     * Login string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/login")
    public String login(Model model) {
        if (authorizerUserService.isLogged()) {
            return "redirect:/";
        }

        model.addAttribute("isLogged", authorizerUserService.isLogged());
        return "auth/loginForm";
    }

    /**
     * Handle string.
     *
     * @return the string
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public String handle(){
        return "error/500";
    }    
}
