package fr.akbarkhan.mediatheque.exception;

import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;

public class ApiException {

    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime time;

    public ApiException(String message, HttpStatus httpStatus, ZonedDateTime time) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTime() {
        return time;
    }
}
