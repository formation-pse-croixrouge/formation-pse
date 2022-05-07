package fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto;

public class ErrorFieldResponse {
  public ErrorFieldResponse(String fieldName, String message) {
    this.fieldName = fieldName;
    this.message = message;
  }

  private final String fieldName;

  private final String message;

  public String getFieldName() {
    return fieldName;
  }

  public String getMessage() {
    return message;
  }
}
