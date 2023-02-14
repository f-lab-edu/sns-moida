package com.moida.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpResponse {

	public static final ResponseEntity<Void> OK = new ResponseEntity<Void>(HttpStatus.OK);
	public static final ResponseEntity<Void> BAD_REQUEST = new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	public static final ResponseEntity<Void> CONFLICT = new ResponseEntity<Void>(HttpStatus.CONFLICT);
	public static final ResponseEntity<Void> CREATE = new ResponseEntity<Void>(HttpStatus.CREATED);
	public static final ResponseEntity<Void> UNAUTHORIZED = new ResponseEntity(HttpStatus.UNAUTHORIZED);
	public static final ResponseEntity<Void> NOT_FOUND = new ResponseEntity(HttpStatus.NOT_FOUND);

}
