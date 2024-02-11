package ru.sberbank.jd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.jd.entity.Cost;

import java.util.UUID;

public interface CostRepository extends JpaRepository<Cost, UUID> {
}
