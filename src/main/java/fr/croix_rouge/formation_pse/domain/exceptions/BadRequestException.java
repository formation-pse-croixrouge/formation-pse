package fr.croix_rouge.formation_pse.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class BadRequestException extends RuntimeException {

  private final String field;


  public BadRequestException(String message, String  field) {
    super(message);
    this.field = field;
  }

  public String getField() {
    return field;
  }
}
