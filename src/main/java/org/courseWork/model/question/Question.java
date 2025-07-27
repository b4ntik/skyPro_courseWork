package org.courseWork.model.question;

import java.util.Objects;

public class Question {
    private String question;
    private String answer;

    public Question() {
//        this.question = question;
//        this.answer = answer;
    }

    //сеттер для вопроса
    public void addQuestion(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getRandomQuestion() {
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question someQuestion = (Question) o;
        return Objects.equals(question, someQuestion.question) && Objects.equals(answer, someQuestion.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }

    public void addQuestion(Question question) {
    }
}
