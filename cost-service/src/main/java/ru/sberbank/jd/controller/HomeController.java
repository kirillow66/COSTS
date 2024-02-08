package ru.sberbank.jd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The type Home controller.
 */
@Controller
public class HomeController {

    /**
     * Home string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping(value = "/")
    public String home(Model model){

        return "home";
    }
    
}
