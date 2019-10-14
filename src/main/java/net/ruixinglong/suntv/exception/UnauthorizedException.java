package net.ruixinglong.suntv.exception;

public class UnauthorizedException extends Exception {
    private String message;

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
