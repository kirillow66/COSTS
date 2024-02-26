package ru.sberbank.jd.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.entity.Cost;
import ru.sberbank.jd.repository.CostRepository;
import ru.sberbank.jd.service.security.AuthorizerUserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The type Cost service.
 */
@Service
@AllArgsConstructor
public class CostService {
    private final CostRepository costRepository;

    /**
     * Create cost.
     *
     * @param cost the cost
     * @return the cost
     */
    public Cost createCost(Cost cost) {
        return costRepository.save(cost);
    }

    /**
     * Update cost.
     *
     * @param cost the cost
     * @return the cost
     */
    public Cost updateCost(Cost cost) {
        if (!costRepository.existsById(cost.getId())) {
            return null;
        }
        return costRepository.save(cost);
    }

    /**
     * Delete cost by uuid.
     *
     * @param id the id
     * @return the uuid
     */
    public UUID deleteCost(UUID id) {
        if (!costRepository.existsById(id)) {
            return null;
        }
        costRepository.deleteById(id);
        return id;
    }

    /**
     * Gets costs.
     *
     * @param pageable the pageable
     * @return the costs
     */
    public Page<Cost> getCosts(Pageable pageable) {
        AuthorizerUserService authorizerUserService = new AuthorizerUserService();
        return costRepository.findAllByUserId(pageable, authorizerUserService.getPrincipalId());
    }

    /**
     * Gets costs.
     *
     * @return the costs
     */
    @PostFilter("@authorizerUserService.isPrincipalId(filterObject.user.id)")
    public List<Cost> getCosts() {
        return costRepository.findAll();
    }

    /**
     * Gets cost by id.
     *
     * @param id the id
     * @return the cost by id
     */
    public Cost getCostById(UUID id) {
        Optional<Cost> cost = costRepository.findById(id);

        return cost.get();
    }

}
