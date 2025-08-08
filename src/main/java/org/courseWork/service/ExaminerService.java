package org.courseWork.service;

import org.courseWork.model.question.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface ExaminerService {
    default Collection<Question> getQuestions(int amount){
        return List.of();
    };

    boolean checkCorrectAnswer(String userAnswer);

    boolean equals(Question question);

    Question getRandomQuestion(int amount);
}
