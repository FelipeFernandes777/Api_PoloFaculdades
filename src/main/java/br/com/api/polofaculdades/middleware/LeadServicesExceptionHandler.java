package br.com.api.polofaculdades.middleware;

import br.com.api.polofaculdades.exception.enterprise.EnterpriseNotFoundException;
import br.com.api.polofaculdades.exception.enterprise.InvalidEnterpriseDataException;
import br.com.api.polofaculdades.exception.lead.InvalidLeadDataException;
import br.com.api.polofaculdades.exception.lead.NotFoundLeadException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LeadServicesExceptionHandler {

    @ExceptionHandler(InvalidLeadDataException.class)
    public ResponseEntity<String> invalidLeadDataException(InvalidLeadDataException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(NotFoundLeadException.class)
    public ResponseEntity<String> notFoundLeadException(NotFoundLeadException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(500).body("Erro interno: " + ex.getMessage());
    }

}
