package ru.sberbank.jd.exception;

/**
 * The type User found.
 */
public class NotAuthorizeFound extends RuntimeException {
    /**
     * Instantiates a new User found.
     */
    public NotAuthorizeFound() {
        super("Нет авторизации");
    }

}