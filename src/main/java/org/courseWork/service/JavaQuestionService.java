package org.courseWork.service;

import org.courseWork.model.question.Question;
import org.springframework.stereotype.Service;

@Service
    public class JavaQuestionService implements QuestionService{
        @Override
        public Question getRandomQuestion() {
            return QuestionService.super.getRandomQuestion();
        }

        @Override
        public boolean checkCorrectAnswer() {
            return false;
        }

        @Override
        public boolean equals(Question question) {
            return false;
        }
}
