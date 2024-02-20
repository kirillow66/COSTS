package ru.sberbank.jd.service;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.controller.component.CostGroupByName;
import ru.sberbank.jd.entity.Cost;
import ru.sberbank.jd.repository.CostRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CostService {
    private final CostRepository costRepository;

    public Cost createCost(Cost cost) {
        return costRepository.save(cost);
    }

    public Cost updateCost(Cost cost) {
        if (!costRepository.existsById(cost.getId())) {
            return null;
        }
        return costRepository.save(cost);
    }

    public UUID deleteCost(UUID id) {
        if (!costRepository.existsById(id)) {
            return null;
        }
        costRepository.deleteById(id);
        return id;
    }

    @PostFilter("@authorizerUserService.isPrincipalId(filterObject.user.id)")
    public List<Cost> getCosts() {
        return costRepository.findAll();
    }

    public Optional<Cost> getCostById(UUID id) {
        return costRepository.findById(id);
    }
    @PostFilter("@authorizerUserService.isPrincipalId(filterObject.userId)")
    public List<CostGroupByName> findCostsByCategory() {
        return costRepository.findCostsGroupByName();
    }
}