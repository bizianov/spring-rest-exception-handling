package app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Map;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {

    private Map<String, List<String>> validationErrors;

    public ValidationException(String message, Map<String, List<String>> validationErrors) {
        super(message);
        this.validationErrors = validationErrors;
    }

    public boolean hasErrors() {
        return !validationErrors.isEmpty();
    }

    public Map<String, List<String>> getValidationErrors() {
        return validationErrors;
    }
}