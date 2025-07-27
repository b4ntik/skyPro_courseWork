package org.courseWork.model.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ThereIsNotQuestionError extends RuntimeException{
    private final ExaminerError error;
    public ThereIsNotQuestionError(){
        super("404 вопрос отсутствует");
        this.error = new ExaminerError("BAD_REQUEST");
    }

    public ExaminerError getError() {   return error; }
}
