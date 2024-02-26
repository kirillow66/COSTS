package ru.sberbank.jd.exception;

/**
 * The type User found.
 */
public class UserFound extends RuntimeException {
    /**
     * Instantiates a new User found.
     */
    public UserFound() {
        super("Пользователь существует. Используйте другой логин.");
    }

}