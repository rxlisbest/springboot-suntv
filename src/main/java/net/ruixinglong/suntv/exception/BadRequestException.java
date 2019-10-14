package net.ruixinglong.suntv.exception;

public class BadRequestException extends Exception {
    private String message;

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
