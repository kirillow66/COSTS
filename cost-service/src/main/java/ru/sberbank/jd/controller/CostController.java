package ru.sberbank.jd.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.jd.entity.Cost;
import ru.sberbank.jd.exception.NotAuthorizeFound;
import ru.sberbank.jd.service.AccountService;
import ru.sberbank.jd.service.CategoryService;
import ru.sberbank.jd.service.CostService;
import ru.sberbank.jd.service.security.AuthorizerUserService;

import java.time.LocalDate;
import java.util.*;

/**
 * The type Cost controller.
 */
@Controller
@RequestMapping("/costs")
@RequiredArgsConstructor
public class CostController {
    private final CostService costService;
    private final CategoryService categoryService;
    private final AccountService accountService;
    private Pageable paging;

    /**
     * Create cost.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/create")
    public String createCost(Model model) {

        model.addAttribute("cost", Cost.builder().date(LocalDate.now()).build());
        model.addAttribute("categories", categoryService.get());
        model.addAttribute("accounts", accountService.getAllAccounts());
        model.addAttribute("pageable", paging);
        model.addAttribute("action", "create");
        return "create-cost";
    }

    /**
     * Save create cost.
     *
     * @param cost  the cost
     * @param model the model
     * @return the string
     */
    @PostMapping("/create")
    public String saveCreateCost(@ModelAttribute @Validated Cost cost, Model model) {
        AuthorizerUserService userService = new AuthorizerUserService();
        cost.setUser(userService.getPrincipal());
        costService.createCost(cost);
        model.addAttribute("costs", cost);

        return "redirect:/costs";
    }

    /**
     * Update cost.
     *
     * @param cost the cost
     * @return the string
     */
    @PostMapping("/update")
    public String updateCost(@ModelAttribute @Validated Cost cost) {
        costService.updateCost(cost);
        return "redirect:/costs?" + "page=" + String.valueOf(paging.getPageNumber() + 1) + "&size=" + paging.getPageSize();
    }

    /**
     * Delete cost.
     *
     * @param id the id
     * @return the string
     */
    @PostMapping("/delete/{id}")
    public String deleteCost(@PathVariable("id") UUID id) {

        costService.deleteCost(id);
        return "redirect:/costs";
    }

    /**
     * Gets cost by id.
     *
     * @param id    the id
     * @param model the model
     * @return the cost by id
     */
    @GetMapping("/{id}")
    public String getCostById(@PathVariable("id") UUID id, Model model) {
        Cost cost = costService.getCostById(id);
        AuthorizerUserService userService = new AuthorizerUserService();

        if (cost.getUser().getId().compareTo(userService.getPrincipalId()) != 0) {
            throw new NotAuthorizeFound();
        }

        model.addAttribute("cost", cost);
        model.addAttribute("categories", categoryService.get());
        model.addAttribute("accounts", accountService.getAllAccounts());
        model.addAttribute("pageable", paging);
        model.addAttribute("action", "update");
        return "create-cost";
    }

    /**
     * Gets costs.
     *
     * @param model the model
     * @param page  the page
     * @param size  the size
     * @return the costs
     */
    @GetMapping
    public String getCosts(Model model, @RequestParam(defaultValue = "1", name = "page") Integer page,
                           @RequestParam(defaultValue = "5", name = "size") Integer size) {
        paging = PageRequest.of(page - 1, size, Sort.by("date").descending());

        Page<Cost> costPage = costService.getCosts(paging);
        costPage.getSort();
        model.addAttribute("Costs", costPage);
        model.addAttribute("pageable", paging);
        model.addAttribute("action", "/costs");

        return "costs";
    }

    /**
     * Handle error.
     *
     * @return the string
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(RuntimeException.class)
    public String handle() {
        return "error/403";
    }
}
