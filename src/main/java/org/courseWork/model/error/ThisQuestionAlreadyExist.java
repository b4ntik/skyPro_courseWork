package org.courseWork.model.error;

public class ThisQuestionAlreadyExist extends IllegalArgumentException {
    public ThisQuestionAlreadyExist(String message) {
        super(message);
    }
}
