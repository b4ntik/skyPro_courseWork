package org.courseWork.service;

import org.courseWork.model.error.ThereIsNotQuestionError;
import org.courseWork.model.error.ThisQuestionAlreadyExist;
import org.courseWork.model.question.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private List<Question> questions = new ArrayList<>();
    //private Random random = new Random();
    //private Question question;
    private JavaQuestionService javaQuestionService;
    public ExaminerServiceImpl() {
    }

    public List<Question> getQuestions(int amount) {
        int index = questions.size();
        if(amount>index){
            throw new ThereIsNotQuestionError();
        }
        List<Question> questionList = new ArrayList<>(questions);
        for(int i = 0; i <=amount; i++){
            questionList.add(getRandomQuestion());
                    }


        return questionList;
    }


    public Question getRandomQuestion() {

        if (questions == null || questions.isEmpty()) {
            throw new ThereIsNotQuestionError();
        }
        List<Question> randomQuestion = (List<Question>) javaQuestionService.getAllQuestions();
        Random random = new Random();
        int randomIndex = random.nextInt(randomQuestion.size());

        return randomQuestion.get(randomIndex);
    }

}
