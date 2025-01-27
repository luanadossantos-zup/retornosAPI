package com.example.retornosAPI.config.exception;

import com.example.retornosAPI.models.Category;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {



    //Handler para gerenciar visualização em JSON (usando for)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
    //Handler para gerenciar visualização em JSON (usando Stream)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = ex.getConstraintViolations()
                .stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(), // Nome do campo
                        ConstraintViolation::getMessage // Mensagem de erro
                ));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    //Handler para gerenciar visualização em JSON de RuntimeException
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    //Handler para a validação do Enum Category
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        boolean isInvalidFormatException = ex.getCause() instanceof InvalidFormatException;

        // Verifica se a causa do erro é um InvalidFormatException
        if (isInvalidFormatException) {
            InvalidFormatException invalidFormatException = (InvalidFormatException) ex.getCause();
            boolean isEnum = invalidFormatException.getTargetType().isEnum();

            // Verifica se o erro está relacionado ao enum Category
            if (isEnum) {
                // Obtém os valores válidos do enum
                 String validValuesForEnum = validValuesForEnum();

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("category: please the only options accepted are: [" + validValuesForEnum + "]");
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request payload");
    }

    public String validValuesForEnum (){
        return String.join(", ",
                Arrays.stream(Category.values())
                        .map(Enum::name)
                        .toArray(String[]::new)
        );
    }


}
