package ru.sberbank.jd.exception;

/**
 * The type User found.
 */
public class UserCantDeleted extends RuntimeException {
    /**
     * Instantiates a new User found.
     */
    public UserCantDeleted(String userName) {
        super("Пользователь '" + userName + "' не может быть удален. Сформированы расходы.");
    }

}