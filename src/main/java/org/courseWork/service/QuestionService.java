package org.courseWork.service;

import org.courseWork.model.question.Question;

import java.util.Collection;
import java.util.List;

public interface QuestionService {
    default Question getRandomQuestion(){return null;};
    Collection<Question> getCollectionOfQuestions = List.of();
    boolean checkCorrectAnswer();
    boolean equals(Question question);
}
