package br.com.api.polofaculdades.middleware;

import br.com.api.polofaculdades.exception.enterprise.EnterpriseNotFoundException;
import br.com.api.polofaculdades.exception.enterprise.InvalidEnterpriseDataException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EnterpriseServicesExceptionHandler {

    @ExceptionHandler(InvalidEnterpriseDataException.class)
    public ResponseEntity<String> invalidEnterpriseDataExceptionHandler() {
        return ResponseEntity.badRequest().body(new InvalidEnterpriseDataException().getMessage());
    }

    @ExceptionHandler(EnterpriseNotFoundException.class)
    public ResponseEntity<String> enterpriseNotFoundExceptionHandler() {
        return ResponseEntity.badRequest().body(new EnterpriseNotFoundException().getMessage());
    }

}
