package com.example.Exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoId0Exception extends RuntimeException {
	public NoId0Exception(String s) {
        super(s);
    }
}
