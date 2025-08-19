package org.courseWork.service;

import org.courseWork.model.question.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


public interface QuestionService {
    default Question getRandomQuestion(){return null;};
    boolean equals(Question question);
}
