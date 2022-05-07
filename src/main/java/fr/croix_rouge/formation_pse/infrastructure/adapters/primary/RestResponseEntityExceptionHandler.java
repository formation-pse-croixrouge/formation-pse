package fr.croix_rouge.formation_pse.infrastructure.adapters.primary;


import fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto.BadRequestResponse;
import fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto.BaseResponse;
import fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto.ErrorFieldResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

//  @ExceptionHandler(Exception.class)
//  public final CustomResponse handleAllExceptions(Exception ex) {
//    return new CustomResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
//  }

//  @ExceptionHandler(value = {BadRequestException.class})
//  protected CustomResponse handleBadRequestException(BadRequestException ex) {
//    return new CustomResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
//  }

//  @ExceptionHandler(MethodArgumentNotValidException.class)
//  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
//    return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
//    return new CustomResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
//  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    BadRequestResponse badRequestResponse = new BadRequestResponse();
    badRequestResponse.setErrorFields(ex.getBindingResult().getFieldErrors().stream().map(error -> new ErrorFieldResponse(error.getField(), error.getDefaultMessage())).collect(Collectors.toList()));
    return new ResponseEntity<>(new BaseResponse(status.value(), "Bad request.", badRequestResponse), status);
  }
}