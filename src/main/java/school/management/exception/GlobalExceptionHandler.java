package school.management.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import school.management.util.Util;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String FIELD_MESSAGE = "Field validation error. Check list of errors.";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> validationHandler(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        Error error = new Error(BAD_REQUEST.value(), System.currentTimeMillis(), FIELD_MESSAGE, errors);

        return ResponseEntity.badRequest().body(error);
    }
}
