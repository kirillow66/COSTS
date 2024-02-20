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
        List<CostGroupByName> costs = reportService.findCostsByCategory();
        model.addAttribute("filter", new CostFilter());
        model.addAttribute("costs", costs);
        return "cost-report";
    }

    @GetMapping("/period")
    public String getCostsByCategoryDate(CostFilter filter, Model model) {
        LocalDate dateFrom = filter.getDateFrom();
        LocalDate dateTo = filter.getDateTo();
        if (dateFrom == null) dateFrom = LocalDate.EPOCH;
        if (dateTo == null) dateTo = LocalDate.of(9999, 12, 31);

        List<CostGroupByName> costs = reportService.findAllByDateBetween(dateFrom, dateTo);
        model.addAttribute("filter", filter);
        model.addAttribute("costs", costs);
        return "cost-report";
    }

}
