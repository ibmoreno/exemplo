package br.com.exemplo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 5358086319829346733L;

    public ApplicationException() {
        super();
    }

    public ApplicationException(String message) {
        super(message);
    }

}
