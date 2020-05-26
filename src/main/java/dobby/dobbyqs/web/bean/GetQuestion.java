package dobby.dobbyqs.web.bean;

import java.util.List;

public class GetQuestion {
    int id;
    String type;
    String question;
    String addition;
    List<String> options;
    String answer;
    String explain;
    String tag;

    public GetQuestion(int id, String type, String question, String addition, List<String> options, String answer, String explain, String tag) {
        this.id = id;
        this.type = type;
        this.question = question;
        this.addition = addition;
        this.options = options;
        this.answer = answer;
        this.explain = explain;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public GetQuestion() {
    }
}
