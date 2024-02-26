package ru.sberbank.jd.exception;

/**
 * The type Not authorize found.
 */
public class NotAuthorizeFound extends RuntimeException {
    /**
     * Instantiates a new Not authorize found.
     */
    public NotAuthorizeFound() {
        super("Нет авторизации");
    }

}