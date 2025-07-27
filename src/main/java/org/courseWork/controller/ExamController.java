package org.courseWork.controller;

import org.courseWork.model.error.ThereIsNotQuestionError;
import org.courseWork.model.question.Question;
import org.courseWork.service.ExaminerService;
import org.courseWork.service.ExaminerServiceImpl;
import org.courseWork.service.QuestionService;
import org.courseWork.service.QuestionStorage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
public class ExamController {
    private final ExaminerService examinerService;
    private final ExaminerServiceImpl examineserviceImpl;
    private final QuestionService questionService;
    private final QuestionStorage questionStorage;

    public ExamController(ExaminerService examinerService, ExaminerServiceImpl examineserviceImpl, QuestionService questionService, QuestionStorage questionStorage) {
        this.examinerService = examinerService;
        this.examineserviceImpl = examineserviceImpl;
        this.questionService = questionService;
        this.questionStorage = questionStorage;
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to SkyExaminer!";
    }

    @GetMapping("exam/get/{amount}")
    public Collection<Question> getRandomQuestions(@PathVariable int amount) {
        Collection<Question> allQuestions = questionStorage.getCollectionsOfQuestions();

        if (allQuestions.size() < amount) {
            throw new ThereIsNotQuestionError();
        }
        List<Question> questionsList = new ArrayList<>(allQuestions);
        Collections.shuffle(questionsList);
        return questionsList.subList(0, amount);
    }

    @GetMapping("exam/java/add")
    public void addQuestion(@RequestParam String text, @RequestParam String answer){
        Question question = new Question();
        question.setQuestion(text);
        question.setAnswer(answer);
        question.addQuestion(question);

    }
}
