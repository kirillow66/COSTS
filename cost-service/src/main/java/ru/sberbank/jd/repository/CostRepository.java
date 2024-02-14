package ru.sberbank.jd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.jd.entity.Cost;

import java.util.UUID;

@Repository
public interface CostRepository extends JpaRepository<Cost, UUID> {
}
