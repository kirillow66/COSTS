package ru.sberbank.jd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sberbank.jd.controller.component.CostGroupByName;
import ru.sberbank.jd.entity.Cost;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * The interface Report repository.
 */
@Repository
public interface ReportRepository extends JpaRepository<Cost, UUID> {

    /**
     * Find all by date between list.
     *
     * @param dateFrom the date from
     * @param dateTo   the date to
     * @return the list
     */
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
