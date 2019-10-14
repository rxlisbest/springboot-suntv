package net.ruixinglong.suntv.exception;

public class ForbiddenException extends Exception {
    private String message;

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
