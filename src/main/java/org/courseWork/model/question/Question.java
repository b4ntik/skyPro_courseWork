package org.courseWork.model.question;

import java.util.Objects;

public class Question {
    private String question;
    private String answer;

    public Question() {

    }

    //добавляем вопрос
    public void addQuestion(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    //меняем вопрос
    public void setQuestion(String question) {
        this.question = question;
    }

    //меняем ответ
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    //получить рандомный вопрос
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

}
