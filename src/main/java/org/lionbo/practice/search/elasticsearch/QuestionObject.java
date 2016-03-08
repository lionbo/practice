package org.lionbo.practice.search.elasticsearch;

public class QuestionObject {

    private String channel;
    private String questionType;
    private String question;
    private String answer;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "QuestionObject [channel=" + channel + ", questionType=" + questionType + ", question=" + question
                + ", answer=" + answer + "]";
    }

}
