package fr.croix_rouge.formation_pse.infrastructure.adapters.primary;


import fr.croix_rouge.formation_pse.domain.exceptions.BadRequestException;
import fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto.BadRequestResponse;
import fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto.BaseResponse;
import fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto.ErrorFieldResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {Exception.class, RuntimeException.class})
  protected ResponseEntity<BaseResponse> handleException(Exception ex) {
    return  new ResponseEntity<>(new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = {BadRequestException.class})
  protected ResponseEntity<BaseResponse> handleBadRequestException(BadRequestException ex) {
    BadRequestResponse badRequestResponse = new BadRequestResponse();
    badRequestResponse.addErrorField(new ErrorFieldResponse(ex.getField(), ex.getMessage()));
    return  new ResponseEntity<>(new BaseResponse(HttpStatus.BAD_REQUEST.value(), "Bad request", badRequestResponse), HttpStatus.BAD_REQUEST);
  }

  @Override
  @NonNull
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
    BadRequestResponse badRequestResponse = new BadRequestResponse();
    badRequestResponse.setErrorFields(ex.getBindingResult().getFieldErrors().stream().map(error -> new ErrorFieldResponse(error.getField(), error.getDefaultMessage())).collect(Collectors.toList()));
    return new ResponseEntity<>(new BaseResponse(status.value(), "Bad request.", badRequestResponse), status);
  }
}