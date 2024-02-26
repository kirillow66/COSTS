package ru.sberbank.jd.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.jd.controller.component.CostGroupByName;
import ru.sberbank.jd.controller.input.CostFilter;
import ru.sberbank.jd.service.ReportService;

import java.util.List;

/**
 * The type Report controller.
 */
@Controller
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    /**
     * Gets costs by category.
     *
     * @param model the model
     * @return the costs by category
     */
    @GetMapping
    public String getCostsByCategory(Model model) {
        CostFilter filter = new CostFilter();
        List<CostGroupByName> costs = reportService.findAllByDateBetween(filter);
        model.addAttribute("filter", filter);
        model.addAttribute("costs", costs);
        return "cost-report";
    }

    /**
     * Gets costs by category date.
     *
     * @param filter the filter
     * @param model  the model
     * @return the costs by category date
     */
    @GetMapping("/period")
    public String getCostsByCategoryDate(CostFilter filter, Model model) {

        List<CostGroupByName> costs = reportService.findAllByDateBetween(filter);
        model.addAttribute("filter", filter);
        model.addAttribute("costs", costs);
        return "cost-report";
    }

}
