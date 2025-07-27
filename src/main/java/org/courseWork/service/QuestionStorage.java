package org.courseWork.service;

import org.courseWork.model.question.Question;

import java.util.Collection;
import java.util.List;

public class QuestionStorage implements QuestionService{
    private final Collection<Question> question;

    public QuestionStorage(Collection<Question> question) {
        this.question = question;
    }

    @Override
    public boolean checkCorrectAnswer() {
        return false;
    }

    @Override
    public boolean equals(Question question) {
        return false;
    }

    public Collection<Question> getCollectionsOfQuestions() {

        return List.of((Question) question);
    }
}
