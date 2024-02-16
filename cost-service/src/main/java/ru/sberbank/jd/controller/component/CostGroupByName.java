package ru.sberbank.jd.controller.component;

import lombok.*;



@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CostGroupByName {
    private String name;
    private Double price;
}
