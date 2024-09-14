package backend.TastyMap.common.exception;

import backend.TastyMap.common.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ErrorResponseDto> handleGeneralException(GeneralException e) {
        ErrorResponseDto errorResponse = e.toErrorResponseDto();
        return new ResponseEntity<>(errorResponse, e.getStatus());
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleNoSuchElementException(NoSuchElementException e) {
        return new ErrorResponseDto(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
