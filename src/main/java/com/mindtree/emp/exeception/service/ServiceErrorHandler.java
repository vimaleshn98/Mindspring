package com.mindtree.emp.exeception.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ServiceErrorHandler {
	@ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler({TrackNotFoundService.class})
    public ResponseEntity<String> handleClassroomServiceException(TrackNotFoundService e) {
        return error(HttpStatus.NOT_FOUND, e);
    }
    @ExceptionHandler({MindNotFoundService.class})
    public ResponseEntity<String> handleClassroomServiceException(MindNotFoundService e) {
        return error(HttpStatus.NOT_FOUND, e);
    }
    
	private ResponseEntity<String> error(HttpStatus status, Exception e) {
        return ResponseEntity.status(status).body(e.getLocalizedMessage());
    }
}
