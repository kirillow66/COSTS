package ru.sberbank.jd.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.jd.controller.component.CostGroupByName;
import ru.sberbank.jd.controller.input.CostFilter;
import ru.sberbank.jd.entity.Category;
import ru.sberbank.jd.entity.Cost;
import ru.sberbank.jd.service.CategoryService;
import ru.sberbank.jd.service.CostService;
import ru.sberbank.jd.service.security.AuthorizerUserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/costs")
@RequiredArgsConstructor
public class CostController {
    private final CostService costService;
    private final CategoryService categoryService;

    @GetMapping("/create")
    public String createCost(Model model) {

        model.addAttribute("cost", new Cost());
        model.addAttribute("categories", categoryService.get());
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

        return "redirect:/costs";
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
        model.addAttribute("action", "update");
        return "create-cost";
    }

    @GetMapping
    public String getCosts(Model model) {
        List<Cost> costs = costService.getCosts();
        model.addAttribute("Costs", costs);
        return "costs";
    }

    @GetMapping("/category")
    public String getCostsByCategory(Model model) {
        List<CostGroupByName> costs = costService.findCostsByCategory();
        model.addAttribute("filter", new CostFilter(LocalDate.MIN, LocalDate.MAX));
        model.addAttribute("costs", costs);
        return "cost-category";
    }
}
