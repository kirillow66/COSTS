package ru.sberbank.jd.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sberbank.jd.controller.component.CostGroupByName;
import ru.sberbank.jd.entity.Cost;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface CostRepository extends JpaRepository<Cost, UUID> {
    @Query("""
            SELECT
             new ru.sberbank.jd.controller.component.CostGroupByName(c.user.id, c.category.name, SUM(c.price))
            FROM
             Cost c
            GROUP BY c.user.id, c.category.name"""
    )
    List<CostGroupByName> findCostsGroupByName();

    List<CostGroupByName> findAllByDateBetween(LocalDate dateFrom, LocalDate dateTo);

}
