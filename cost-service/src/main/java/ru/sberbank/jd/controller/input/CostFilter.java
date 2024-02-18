package ru.sberbank.jd.controller.input;

import java.time.LocalDate;

public record CostFilter(LocalDate dateFrom, LocalDate dateTo) {
}
