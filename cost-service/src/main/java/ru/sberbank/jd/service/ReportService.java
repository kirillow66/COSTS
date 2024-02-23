package ru.sberbank.jd.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.controller.component.CostGroupByName;
import ru.sberbank.jd.controller.input.CostFilter;
import ru.sberbank.jd.entity.Cost;
import ru.sberbank.jd.repository.CostRepository;
import ru.sberbank.jd.repository.ReportRepository;
import ru.sberbank.jd.service.security.AuthorizerUserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    @PostFilter("@authorizerUserService.isPrincipalId(filterObject.userId)")
    public List<CostGroupByName> findAllByDateBetween(CostFilter filter) {
        LocalDate dateFrom = filter.getDateFrom();
        LocalDate dateTo = filter.getDateTo();
        if (dateFrom == null) dateFrom = LocalDate.EPOCH;
        if (dateTo == null) dateTo = LocalDate.of(9999, 12, 31);

        return reportRepository.findAllByDateBetween(dateFrom, dateTo);

    }
}
