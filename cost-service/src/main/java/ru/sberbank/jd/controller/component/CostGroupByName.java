package ru.sberbank.jd.controller.component;

import lombok.*;

import java.util.UUID;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CostGroupByName {
    private UUID userId;
    private String name;
    private Double price;
}
