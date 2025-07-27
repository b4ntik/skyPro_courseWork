package org.courseWork.model.error;

public class ThereIsNotQuestionError extends RuntimeException{
    private final ExaminerError error;
    public ThereIsNotQuestionError(){
        super("404 вопрос отсутствует");
        this.error = new ExaminerError("404 - такой вопрос отсутствует");
    }

    public ExaminerError getError() {   return error; }
}
