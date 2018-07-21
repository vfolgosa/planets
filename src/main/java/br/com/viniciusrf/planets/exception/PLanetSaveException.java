package br.com.viniciusrf.planets.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PLanetSaveException extends Exception{
	
	private static final long serialVersionUID = 6797410591727975574L;

	public PLanetSaveException(String message) {
		super(message);
	}

}
