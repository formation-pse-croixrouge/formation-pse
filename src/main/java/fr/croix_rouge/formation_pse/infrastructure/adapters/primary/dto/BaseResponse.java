package fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto;

public class BaseResponse {
  private int status;
  private String message;
  public Object getBody() {
    return body;
  }

  public BaseResponse(int status, String message, Object body) {
    this.status = status;
    this.message = message;
    this.body = body;
  }

  public BaseResponse(int status, String message) {
    this.status = status;
    this.message = message;
    this.body = null;
  }

  public void setBody(Object body) {
    this.body = body;
  }

  private Object body;

  public int getStatus() {
    return status;
  }

  public BaseResponse setStatus(int status) {
    this.status = status;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public BaseResponse setMessage(String message) {
    this.message = message;
    return this;
  }

}
