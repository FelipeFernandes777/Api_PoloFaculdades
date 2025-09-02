package br.com.api.polofaculdades.middleware;

import br.com.api.polofaculdades.exception.enterprise.EnterpriseNotFoundException;
import br.com.api.polofaculdades.exception.enterprise.InvalidEnterpriseDataException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EnterpriseServicesExceptionHandler {

    @ExceptionHandler(InvalidEnterpriseDataException.class)
    public ResponseEntity<String> invalidEnterpriseDataExceptionHandler(InvalidEnterpriseDataException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(EnterpriseNotFoundException.class)
    public ResponseEntity<String> enterpriseNotFoundExceptionHandler(EnterpriseNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(500).body("Erro interno: " + ex.getMessage());
    }

}
