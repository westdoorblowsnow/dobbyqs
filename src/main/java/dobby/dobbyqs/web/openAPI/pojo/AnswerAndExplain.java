package dobby.dobbyqs.web.openAPI.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AnswerAndExplain {
    private Integer questionId;
    private String answer;
    private String explain;
    @JsonIgnore
    private Integer answerI;

    public AnswerAndExplain init(){
        StringBuilder a = new StringBuilder();
        for (char i = 'A'; i <= 'O'; i++) {
            if ((answerI & (1 << ('O' - i))) != 0) {
                a.append(i);
            }
        }
        answer = a.toString();
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AnswerAndExplain{");
        sb.append("questionId=").append(questionId);
        sb.append(", answer='").append(answer).append('\'');
        sb.append(", explain='").append(explain).append('\'');
        sb.append(", answerI=").append(answerI);
        sb.append('}');
        return sb.toString();
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public Integer getAnswerI() {
        return answerI;
    }

    public void setAnswerI(Integer answerI) {
        this.answerI = answerI;
    }

    public AnswerAndExplain(Integer questionId, String answer, String explain, Integer answerI) {
        this.questionId = questionId;
        this.answer = answer;
        this.explain = explain;
        this.answerI = answerI;
    }

    public AnswerAndExplain() {
    }
}
