package ru.sberbank.jd.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.sberbank.jd.entity.Cost;
import ru.sberbank.jd.repository.CostRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Import(TestConfig.class)
@ExtendWith(MockitoExtension.class)
class CostServiceTest {
    @MockBean
    private CostRepository costRepository;
    @Autowired
    private CostService costService;

    @Test
    void findById() {
        Cost cost = new Cost();
        UUID costId = UUID.randomUUID();
        cost.setId(costId);
        Mockito.when(costRepository.findById(costId)).thenReturn(Optional.of(cost));
        Cost testCost = costService.getCostById(costId);
        Assertions.assertEquals(cost, testCost);
    }

    @Test
    void create() {
        Cost cost = new Cost().builder().name("Еда").date(LocalDate.now()).price(350.0).build();
        Mockito.when(costRepository.save(cost))
                .thenReturn(cost);

        Cost testCost = costService.createCost(cost);

        Assertions.assertEquals(cost.getDate(), testCost.getDate());
        Assertions.assertEquals(cost.getName(),
                testCost.getName());
    }
}