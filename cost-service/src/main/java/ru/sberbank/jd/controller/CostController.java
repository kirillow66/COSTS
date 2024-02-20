package ru.sberbank.jd.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.mapstruct.ap.shaded.freemarker.template.utility.DateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.jd.controller.component.CostGroupByName;
import ru.sberbank.jd.controller.input.CostFilter;
import ru.sberbank.jd.entity.Cost;
import ru.sberbank.jd.service.AccountService;
import ru.sberbank.jd.service.CategoryService;
import ru.sberbank.jd.service.CostService;
import ru.sberbank.jd.service.security.AuthorizerUserService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/costs")
@RequiredArgsConstructor
public class CostController {
    private final CostService costService;
    private final CategoryService categoryService;
    private final AccountService accountService;
    private Pageable paging;

    @GetMapping("/create")
    public String createCost(Model model) {

        model.addAttribute("cost", new Cost());
        model.addAttribute("categories", categoryService.get());
        model.addAttribute("accounts", accountService.getAllAccounts());
        model.addAttribute("pageable", paging);
        model.addAttribute("action", "create");
        return "create-cost";
    }

    @PostMapping("/create")
    public String saveCreateCost(@ModelAttribute @Validated Cost cost, Model model) {
        AuthorizerUserService userService = new AuthorizerUserService();
        cost.setUser(userService.getPrincipal());
        costService.createCost(cost);
        model.addAttribute("costs", cost);

        return "redirect:/costs";
    }

    @PostMapping("/update")
    public String updateCost(@ModelAttribute @Validated Cost cost) {
        costService.updateCost(cost);
        return "redirect:/costs?" + "page=" + String.valueOf(paging.getPageNumber() + 1) + "&size=" + paging.getPageSize();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCost(@PathVariable("id") UUID id) {
        UUID deleteId = costService.deleteCost(id);

        return Objects.isNull(deleteId)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(deleteId);
    }

    @GetMapping("/{id}")
    public String getCostById(@PathVariable("id") UUID id, Model model) {
        Optional<Cost> cost = costService.getCostById(id);
        model.addAttribute("cost", cost.get());
        model.addAttribute("categories", categoryService.get());
        model.addAttribute("accounts", accountService.getAllAccounts());
        model.addAttribute("pageable", paging);
        model.addAttribute("action", "update");
        return "create-cost";
    }

    @GetMapping
    public String getCosts(Model model, @RequestParam(defaultValue = "1", name = "page") Integer page,
                           @RequestParam(defaultValue = "5", name = "size") Integer size) {
        paging = PageRequest.of(page - 1, size);

        Page<Cost> costPage = costService.getCosts(paging);

        model.addAttribute("Costs", costPage);
        model.addAttribute("pageable", paging);
        model.addAttribute("action", "/costs");

        return "costs";
    }
}
