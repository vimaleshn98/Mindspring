package com.mindtree.emp.exeception.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllerErrorHandler {
	@ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler({TrackNotFound.class})
    public ResponseEntity<String> handleClassroomServiceException(TrackNotFound e) {
        return error(HttpStatus.NOT_FOUND, e);
    }
    @ExceptionHandler({MindNotFound.class})
    public ResponseEntity<String> handleClassroomServiceException(MindNotFound e) {
        return error(HttpStatus.NOT_FOUND, e);
    }
    
	private ResponseEntity<String> error(HttpStatus status, Exception e) {
        return ResponseEntity.status(status).body(e.getLocalizedMessage());
    }
}
