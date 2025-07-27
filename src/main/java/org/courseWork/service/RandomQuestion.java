package org.courseWork.service;

import org.courseWork.model.error.ThereIsNotQuestionError;
import org.courseWork.model.question.Question;

import java.util.List;
import java.util.Random;

public class RandomQuestion {
    private List<Question> questions;
    private Random random = new Random();

    public RandomQuestion(List<Question> questions){
        this.questions = questions;
    };


        public Question getRandomQuestion() {
            if(questions == null || questions.isEmpty()){
                throw new ThereIsNotQuestionError();
            }
            int index = random.nextInt(questions.size());
            return questions.get(index);

        }
    }

