package br.com.api.polofaculdades.exception.lead;

public class NotFoundLeadException extends RuntimeException {
  public NotFoundLeadException(String message) {
    super(message);
  }
}
