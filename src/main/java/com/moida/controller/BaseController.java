package com.moida.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {

	protected <T> ResponseEntity<T> ok(T data) {
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	protected <T> ResponseEntity<T> created(T data) {
		return new ResponseEntity<>(data, HttpStatus.CREATED);
	}

	protected ResponseEntity<Void> noContent() {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
