package org.example.maids.advice;

import jakarta.persistence.EntityNotFoundException;
import org.example.maids.constants.ErrorCodes;
import org.example.maids.exceptions.ReturnDateException;
import org.example.maids.models.ApiErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ApiExceptionHandlerTest {

    @InjectMocks
    ApiExceptionHandler apiExceptionHandler;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void handleReturnDateExceptionTest() {
        ReturnDateException ex = new ReturnDateException("Test exception");
        ResponseEntity<ApiErrorResponse> response = apiExceptionHandler.handleReturnDateException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ErrorCodes.RETURN_DATE_ERROR.getErrorCode(), response.getBody().getErrorCode());
    }

    @Test
    public void entityNotFoundExceptionTest() {
        EntityNotFoundException ex = new EntityNotFoundException("Test exception");
        ResponseEntity<ApiErrorResponse> response = apiExceptionHandler.entityNotFoundException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ErrorCodes.ENTITY_NOTFOUND_ERROR.getErrorCode(), response.getBody().getErrorCode());
    }

    @Test
    public void handleValidationExceptionTest() {
        BindException ex = new BindException(new Object(), "Test object");
        ex.addError(new FieldError("Test object", "Test field", "Test message"));
        ResponseEntity<ApiErrorResponse> response = apiExceptionHandler.handleValidationException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ErrorCodes.OBJECT_VALIDATION_ERROR.getErrorCode(), response.getBody().getErrorCode());
    }
}
