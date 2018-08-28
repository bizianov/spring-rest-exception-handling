package app.controller;

import app.exception.CarNotFoundException;
import app.exception.NoCustomCarExistsException;
import app.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ve) {
        Map<String, Object> responseObject = new HashMap<>();
        responseObject.put("status", "error");
        responseObject.put("message", ve.getMessage());
        if (ve.hasErrors()) {
            responseObject.put("validationErrors", ve.getValidationErrors());
        }

        return ResponseEntity.badRequest()
                .body(responseObject);
    }

    @ExceptionHandler(value = {CarNotFoundException.class, NoCustomCarExistsException.class})
    public ResponseEntity<Object> handleCarNotFoundException(RuntimeException re) {
        Map<String, Object> responseObject = new HashMap<>();
        responseObject.put("status", "error");
        responseObject.put("message", re.getMessage());

        return ResponseEntity.badRequest()
                .body(responseObject);
    }
}