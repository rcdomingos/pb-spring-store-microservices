package com.compass.pb.exams.orders.handler;

import com.compass.pb.exams.orders.domain.response.DefaultMessageResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultMessageResponse> handleValidationExceptions(MethodArgumentNotValidException e,
                                                                             HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errorMessage = new ArrayList<>();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        fieldErrors.forEach(ex -> {
            String msgError = "O campo '" + ex.getField() + "' " + ex.getDefaultMessage();
            errorMessage.add(msgError);
        });
        return ResponseEntity.status(status).body(new DefaultMessageResponse(String.valueOf(status.value()), errorMessage));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DefaultMessageResponse> handleHttpValidationExceptions(HttpMessageNotReadableException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String errorMessage = "Invalid information";

        if (e.getCause() instanceof InvalidFormatException) {
            InvalidFormatException cause = (InvalidFormatException) e.getCause();
            errorMessage = cause.getCause().getMessage();
        }
        return ResponseEntity.status(status).body(new DefaultMessageResponse(String.valueOf(status.value()), errorMessage));
    }
}
