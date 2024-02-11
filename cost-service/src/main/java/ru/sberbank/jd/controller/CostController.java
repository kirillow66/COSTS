package ru.sberbank.jd.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.jd.entity.Cost;
import ru.sberbank.jd.service.CostService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/costs/")
@RequiredArgsConstructor
public class CostController {
    private final CostService costService;

    @PostMapping
    public ResponseEntity createCost(@RequestBody Cost cost) {
        return ResponseEntity.ok(costService.createCost(cost));
    }

    @PutMapping
    public ResponseEntity updateCost(@RequestBody Cost cost) {
        Cost updateCost = costService.updateCost(cost);

        return Objects.isNull(updateCost)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(updateCost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCost(@PathVariable("id") UUID id) {
        UUID deleteId = costService.deleteCost(id);

        return Objects.isNull(deleteId)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(deleteId);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCostById(@PathVariable("id") UUID id) {
        Optional<Cost> cost = costService.getCostById(id);

        return cost.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(cost);
    }

    @GetMapping
    public ResponseEntity getCosts(Model model) {
        Optional<List<Cost>> costs = costService.getCosts();
        if (costs.isEmpty()) {
            ResponseEntity.notFound().build();
        }
        model.addAttribute("Costs", costs);
        return ResponseEntity.ok(costs);
    }
}
