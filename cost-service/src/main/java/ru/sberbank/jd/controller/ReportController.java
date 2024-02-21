package ru.sberbank.jd.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.jd.controller.component.CostGroupByName;
import ru.sberbank.jd.controller.input.CostFilter;
import ru.sberbank.jd.service.CostService;
import ru.sberbank.jd.service.ReportService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping
    public String getCostsByCategory(Model model) {
        CostFilter filter = new CostFilter();
        List<CostGroupByName> costs = reportService.findAllByDateBetween(filter);
        model.addAttribute("filter", filter);
        model.addAttribute("costs", costs);
        return "cost-report";
    }

    @GetMapping("/period")
    public String getCostsByCategoryDate(CostFilter filter, Model model) {

        List<CostGroupByName> costs = reportService.findAllByDateBetween(filter);
        model.addAttribute("filter", filter);
        model.addAttribute("costs", costs);
        return "cost-report";
    }

}
