package flow.work.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalApiExceptionHandler {
    private static final String LOG_FORMAT = "Error Message : {}";

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<String> handleApplicationException(ApplicationException ex) {
        log.error(LOG_FORMAT, ex.getMessage());
        return ResponseEntity.status(ex.httpStatus).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleInternalServerException(Exception ex) {
        log.error(LOG_FORMAT, ex.getStackTrace());
        log.error("errorMessage : {}", ex.getMessage());
        return ResponseEntity.internalServerError().body("실패하였습니다. 계속된 문제 발생시 문의 부탁드립니다.");
    }
}