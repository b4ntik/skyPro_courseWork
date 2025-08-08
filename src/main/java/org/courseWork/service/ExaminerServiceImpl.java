package org.courseWork.service;

import org.courseWork.model.error.ThereIsNotQuestionError;
import org.courseWork.model.error.ThisQuestionAlreadyExist;
import org.courseWork.model.question.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private Set<Question> questions = new HashSet<>();

    public ExaminerServiceImpl() {
    }

    //только из-за того, что в интерфейсе эти методы есть
    @Override
    public boolean checkCorrectAnswer(String userAnswer) {
        return false;
    }

    @Override
    public boolean equals(Question question) {
        return false;
    }

    @Override
    public Question getRandomQuestion(int amount) {
        return null;
    }

}
