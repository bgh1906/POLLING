package com.polling.exception;

import static com.polling.exception.CustomErrorResult.DUPLICATE_RESOURCE;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object>
  handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
      final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    final List<String> errorList = ex
        .getBindingResult()
        .getAllErrors()
        .stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.toList());
    return this.makeErrorResponseEntity(errorList.toString());
  }

  /*hibernate 관련 exception 처리*/
  @ExceptionHandler(value = {ConstraintViolationException.class,
      DataIntegrityViolationException.class})
  protected ResponseEntity<ErrorResponse> handleDataException() {
    return this.makeErrorResponseEntity(DUPLICATE_RESOURCE);
  }

  private ResponseEntity<Object> makeErrorResponseEntity(final String errorDescription) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), errorDescription));
  }

  @ExceptionHandler({CustomException.class})
  public ResponseEntity<ErrorResponse> handleRestApiException(final CustomException exception) {
    return this.makeErrorResponseEntity(exception.getCustomErrorResult());
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<ErrorResponse> handleException(final Exception exception) {
    return this.makeErrorResponseEntity(exception);
  }

  private ResponseEntity<ErrorResponse> makeErrorResponseEntity(
      final CustomErrorResult errorResult) {
    return ResponseEntity.status(errorResult.getHttpStatus())
        .body(new ErrorResponse(errorResult.name(), errorResult.getDetail()));
  }

  private ResponseEntity<ErrorResponse> makeErrorResponseEntity(final Exception exception) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
            new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(), exception.getMessage()));
  }

  @Getter
  @RequiredArgsConstructor
  static class ErrorResponse {

    private final String code;
    private final String message;
  }
}
