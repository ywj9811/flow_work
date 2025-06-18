package flow.work.exception;

import lombok.Getter;

@Getter
public abstract class ApplicationException extends RuntimeException{
    protected ApplicationException(String message) {
        super(message);
    }
}
