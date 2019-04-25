package com.ibm.diypopups.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DiyPopupsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DiyPopupsException(String message) {
        super(message);
    }

    public DiyPopupsException(String message, Throwable cause) {
        super(message, cause);
    }
}
