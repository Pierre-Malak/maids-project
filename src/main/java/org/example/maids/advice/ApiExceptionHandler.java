package org.example.maids.advice;

import jakarta.persistence.EntityNotFoundException;
import org.example.maids.constants.ErrorCodes;
import org.example.maids.exceptions.ReturnDateException;
import org.example.maids.models.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ReturnDateException.class)
    public ResponseEntity<ApiErrorResponse> handleReturnDateException(ReturnDateException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse(ErrorCodes.RETURN_DATE_ERROR.getErrorCode(), ex.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> entityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse(ErrorCodes.ENTITY_NOTFOUND_ERROR.getErrorCode(), ex.getMessage()));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(BindException ex) {
        FieldError fieldError = ex.getFieldError();
        String errorMessage = fieldError != null ? fieldError.getDefaultMessage() : "Validation failed";
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(ErrorCodes.OBJECT_VALIDATION_ERROR.getErrorCode(), errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }

}
