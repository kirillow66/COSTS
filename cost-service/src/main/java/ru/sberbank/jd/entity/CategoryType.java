package ru.sberbank.jd.entity;

import lombok.Getter;

/**
 * The enum Category type.
 */
@Getter
public enum CategoryType {
    /**
     * The None.
     */
    NONE("No category"),
    /**
     * Debit category type.
     */
    DEBIT("Debit"),
    /**
     * Credit category type.
     */
    CREDIT("Credit");
    private final String name;

    CategoryType(String name) {
        this.name = name;
    }
}
