package br.edu.ifsp.arq.exape_dw2.config;

import br.edu.ifsp.arq.exape_dw2.domain.exception.BadRequestException;
import br.edu.ifsp.arq.exape_dw2.domain.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerConfiguration extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity resourceNotFound(final NotFoundException exception, final HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity resourceInvalid(final BadRequestException exception, final HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }


}
