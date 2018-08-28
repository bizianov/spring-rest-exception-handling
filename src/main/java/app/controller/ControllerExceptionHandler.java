package app.controller;

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
        Map<String, Object> responseObect = new HashMap<>();
        responseObect.put("status", "error");
        responseObect.put("message", ve.getMessage());
        if (ve.hasErrors()) {
            responseObect.put("validationErrors", ve.getValidationErrors());
        }

        return ResponseEntity.badRequest()
                .body(responseObect);
    }
}