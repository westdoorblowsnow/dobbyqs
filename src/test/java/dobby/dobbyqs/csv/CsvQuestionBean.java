package dobby.dobbyqs.csv;

import com.opencsv.bean.CsvBindByName;

import java.util.StringJoiner;

public class CsvQuestionBean {
    @CsvBindByName(column = "题型")
    private Integer type;
    @CsvBindByName(column = "题目")
    private String question;
    @CsvBindByName(column = "题目内容")
    private String addition;
    @CsvBindByName(column = "选项")
    private String options;
    @CsvBindByName(column = "正确答案")
    private String answer;
    @CsvBindByName(column = "分数")
    private Integer score;
    @CsvBindByName(column = "答案解析")
    private String explain;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CsvQuestionBean.class.getSimpleName() + "[", "]")
                .add("type=" + type)
                .add("question='" + question + "'")
                .add("addition='" + addition + "'")
                .add("options='" + options + "'")
                .add("answer='" + answer + "'")
                .add("score=" + score)
                .add("explain='" + explain + "'")
                .toString();
    }
}
