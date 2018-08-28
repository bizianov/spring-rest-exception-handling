package app.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationErrorBuilder {

    private Map<String, List<String>> errors;

    public ValidationErrorBuilder() {
        errors = new HashMap<>();
    }

    public void addError(String field, String message) {
        errors.computeIfAbsent(field, k -> new ArrayList<>()).add(message);
    }

    public Map<String, List<String>> getErrors() {
        return errors;
    }
}