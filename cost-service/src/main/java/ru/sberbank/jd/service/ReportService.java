package ru.sberbank.jd.service;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.controller.component.CostGroupByName;
import ru.sberbank.jd.controller.input.CostFilter;
import ru.sberbank.jd.repository.ReportRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * The type Report service.
 */
@Service
@AllArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    /**
     * Find all by date between list.
     *
     * @param filter the filter
     * @return the list
     */
    @PostFilter("@authorizerUserService.isPrincipalId(filterObject.userId)")
    public List<CostGroupByName> findAllByDateBetween(CostFilter filter) {
        LocalDate dateFrom = filter.getDateFrom();
        LocalDate dateTo = filter.getDateTo();
        if (dateFrom == null) dateFrom = LocalDate.EPOCH;
        if (dateTo == null) dateTo = LocalDate.of(9999, 12, 31);

        return reportRepository.findAllByDateBetween(dateFrom, dateTo);

    }
}
