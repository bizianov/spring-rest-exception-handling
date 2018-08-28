package app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NoCustomCarExistsException extends RuntimeException {

    public NoCustomCarExistsException(String message) {
        super(message);
    }
}