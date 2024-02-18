package ru.sberbank.jd.exception;

/**
 * The type User not found.
 */
public class UserNotFound extends RuntimeException {
    /**
     * Instantiates.
     */
    public UserNotFound() {
        super("Пользователь не существует");
    }

}