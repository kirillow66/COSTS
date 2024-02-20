package ru.sberbank.jd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sberbank.jd.controller.component.CostGroupByName;
import ru.sberbank.jd.entity.Cost;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface CostRepository extends JpaRepository<Cost, UUID> {
    Page<Cost> findAllByUserId(Pageable pageable, UUID id);
}
