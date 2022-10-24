package cl.tenpo.evaluation.authservice.controller;

import cl.tenpo.evaluation.authservice.exception.BadRequestException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {
    public static final String ERROR_RESPONSE_CAUSE = "cause";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public Map<String, String> handleBadRequestExceptions(
        BadRequestException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(ERROR_RESPONSE_CAUSE, ex.getLocalizedMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(SignatureException.class)
    public Map<String, String> handleJwtSignatureExceptions(
        SignatureException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(ERROR_RESPONSE_CAUSE, ex.getLocalizedMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(MalformedJwtException.class)
    public Map<String, String> handleMalformedJwtException(
        MalformedJwtException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(ERROR_RESPONSE_CAUSE, ex.getLocalizedMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnsupportedJwtException.class)
    public Map<String, String> handleJwtUnsupportedExceptions(
        UnsupportedJwtException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(ERROR_RESPONSE_CAUSE, ex.getLocalizedMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ExpiredJwtException.class)
    public Map<String, String> handleExpiredJwtException(
        ExpiredJwtException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(ERROR_RESPONSE_CAUSE, ex.getLocalizedMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String, String> handleIllegalArgumentException(
        IllegalArgumentException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(ERROR_RESPONSE_CAUSE, ex.getLocalizedMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(MissingRequestHeaderException.class)
    public Map<String, String> MissingRequestHeaderException(
        MissingRequestHeaderException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(ERROR_RESPONSE_CAUSE, ex.getLocalizedMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> methodArgumentNotValidException(
        MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(ERROR_RESPONSE_CAUSE, ex.getMessage());
        return errors;
    }


}
