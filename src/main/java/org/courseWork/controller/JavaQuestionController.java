package org.courseWork.controller;

import org.courseWork.model.error.ThereIsNotQuestionError;
import org.courseWork.model.question.Question;
import org.courseWork.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;





@RestController
public class JavaQuestionController {
    private final JavaQuestionService javaQuestionService;

    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @GetMapping("exam/java/add")
    public void addQuestion(@RequestParam String text, @RequestParam String answer) {
        Question question = new Question();
        question.setQuestion(text);
        question.setAnswer(answer);
        javaQuestionService.addQuestion(question);

    }

    @GetMapping("exam/java/remove")
    public void removeQuestions(@RequestParam String text) {
        javaQuestionService.removeQuestion(text);
    }
}
