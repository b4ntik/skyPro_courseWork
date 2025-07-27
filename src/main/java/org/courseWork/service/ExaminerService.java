package org.courseWork.service;

import org.courseWork.model.question.Question;

import java.util.Collection;
import java.util.List;

public interface ExaminerService {
    default Collection<Question> getQuestions(int amount){
        return List.of();
    };

}
