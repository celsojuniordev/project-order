package com.br.projectorder.controller.handler;

import com.br.projectorder.exception.NotFoundException;
import com.br.projectorder.exception.UnprocessableEntityException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandlers extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach( error ->
                errors.add(error.getDefaultMessage()));

        String defaultMessage = "Campo(s) inválido(s)";
        ApiErrorList error = new ApiErrorList(HttpStatus.BAD_REQUEST.value(), defaultMessage, new Date(), errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<ApiError> handlerUnprocessableEntityException(UnprocessableEntityException ex) {
        ApiError error = new ApiError(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage(), new Date());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handlerNotFoundException(NotFoundException ex){
        ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), new Date());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
