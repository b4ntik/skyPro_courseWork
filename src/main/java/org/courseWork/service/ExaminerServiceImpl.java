package org.courseWork.service;

import org.courseWork.model.error.ThereIsNotQuestionError;
import org.courseWork.model.error.ThisQuestionAlreadyExist;
import org.courseWork.model.question.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private JavaQuestionService javaQuestionService;

    public ExaminerServiceImpl() {
    }

    public List<Question> getQuestions(int amount) {
        int index = javaQuestionService.getAllQuestions().size();
        if(amount>index){
            throw new ThereIsNotQuestionError();
        }
        List<Question> questionList = new ArrayList<>(javaQuestionService.getAllQuestions());
        for(int i = 0; i <=amount; i++){
            questionList.add(javaQuestionService.getRandomQuestion());
                    }


        return questionList;
    }




}
