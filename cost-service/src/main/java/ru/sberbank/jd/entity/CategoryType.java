package ru.sberbank.jd.entity;

import lombok.Getter;

@Getter
public enum CategoryType {
    NONE("No category"),
    DEBIT("Debit"),
    CREDIT("Credit");
    private final String name;

    private CategoryType(String name) {
        this.name = name;
    }
}
