package br.com.api.polofaculdades.exception.enterprise;

public class EnterpriseNotFoundException extends RuntimeException {
    public EnterpriseNotFoundException(String message) {
        super(message);
    }
}