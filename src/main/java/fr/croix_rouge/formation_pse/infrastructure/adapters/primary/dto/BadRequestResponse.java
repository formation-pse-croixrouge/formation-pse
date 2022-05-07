package fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto;

import java.util.ArrayList;
import java.util.List;

public class BadRequestResponse {

  private List<ErrorFieldResponse> errorFields;

  public BadRequestResponse() {
    this.errorFields = new ArrayList<ErrorFieldResponse>();
  }

  public List<ErrorFieldResponse> getErrorFields() {
    return errorFields;
  }

  public void setErrorFields(List<ErrorFieldResponse> errorFields) {
    this.errorFields = errorFields;
  }
}
