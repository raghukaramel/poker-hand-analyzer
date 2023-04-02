package com.quicken.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Global Exception Handler for REST Controllers.
 * It allows us to handle exceptions from all REST Controllers at a central place.
 *
 * @author Raghu Karamel
 */
@RestControllerAdvice // enables global interceptor for handling exceptions REST controllers
public class GlobalExceptionHandler {

    /**
     * Customized MethodArgumentNotValidException handler default behavior.
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // set response code as 400
    public Map<String, String> handleMethodArgumentException(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }
}