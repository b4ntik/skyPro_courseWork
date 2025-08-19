package org.courseWork.model.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExamControllerAdvice {
    @ExceptionHandler(ThereIsNotQuestionError.class)
    public ResponseEntity<String> handleNoQuestions(ThereIsNotQuestionError ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
}
