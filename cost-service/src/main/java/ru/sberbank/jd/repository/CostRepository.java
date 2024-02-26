package ru.sberbank.jd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import ru.sberbank.jd.entity.Cost;

import java.util.UUID;

/**
 * The interface Cost repository.
 */
@Repository
public interface CostRepository extends JpaRepository<Cost, UUID> {
    /**
     * Find all by user id page.
     *
     * @param pageable the pageable
     * @param id       the id
     * @return the page
     */
    Page<Cost> findAllByUserId(Pageable pageable, UUID id);
}
