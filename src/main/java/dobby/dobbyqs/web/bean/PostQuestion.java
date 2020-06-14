package dobby.dobbyqs.web.bean;

import java.util.List;

public class PostQuestion {
    String question;
    String answer;
    String explain;
    List<String> options;
    String addition;
    String type;
    String tag;
    Integer subjectId;
    String group;
    Integer num;
    Integer scoe;

    @Override
    public String toString() {
        return "PostQuestion{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", explain='" + explain + '\'' +
                ", options=" + options +
                ", addition='" + addition + '\'' +
                ", type='" + type + '\'' +
                ", tag='" + tag + '\'' +
                ", subjectId=" + subjectId +
                ", group='" + group + '\'' +
                ", num=" + num +
                ", scoe=" + scoe +
                '}';
    }

    public Integer getScoe() {
        return scoe;
    }

    public void setScoe(Integer scoe) {
        this.scoe = scoe;
    }

    public PostQuestion(String question, String answer, String explain, List<String> options, String addition, String type, String tag, Integer subjectId, String group, Integer num, Integer scoe) {
        this.question = question;
        this.answer = answer;
        this.explain = explain;
        this.options = options;
        this.addition = addition;
        this.type = type;
        this.tag = tag;
        this.subjectId = subjectId;
        this.group = group;
        this.num = num;
        this.scoe = scoe;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public PostQuestion(String question, String answer, String explain, List<String> options, String addition, String type, String tag, Integer subjectId, String group) {
        this.question = question;
        this.answer = answer;
        this.explain = explain;
        this.options = options;
        this.addition = addition;
        this.type = type;
        this.tag = tag;
        this.subjectId = subjectId;
        this.group = group;
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

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public PostQuestion(String question, String answer, String explain, List<String> options, String addition, String type, String tag, Integer subjectId) {
        this.question = question;
        this.answer = answer;
        this.explain = explain;
        this.options = options;
        this.addition = addition;
        this.type = type;
        this.tag = tag;
        this.subjectId = subjectId;
    }

    public PostQuestion() {
    }

}
