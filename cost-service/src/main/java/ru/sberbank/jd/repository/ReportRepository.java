package ru.sberbank.jd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sberbank.jd.controller.component.CostGroupByName;
import ru.sberbank.jd.entity.Cost;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReportRepository extends JpaRepository<Cost, UUID> {
    @Query("""
            SELECT
             new ru.sberbank.jd.controller.component.CostGroupByName(c.user.id, c.category.name, SUM(c.price))
            FROM
             Cost c
            GROUP BY c.user.id, c.category.name"""
    )
    List<CostGroupByName> findCostsGroupByName();

    Page<Cost> findAllByUserId(Pageable pageable, UUID id);

    @Query("""
            SELECT
             new ru.sberbank.jd.controller.component.CostGroupByName(c.user.id, c.category.name, SUM(c.price))
            FROM
             Cost c
            where c.date between :dateFrom and :dateTo
            GROUP BY c.user.id, c.category.name"""
    )
    List<CostGroupByName> findAllByDateBetween(@Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);

}
