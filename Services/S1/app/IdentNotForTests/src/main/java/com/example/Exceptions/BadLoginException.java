package com.example.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class BadLoginException extends RuntimeException{
	public BadLoginException(String s) {
        super(s);
    }
}
