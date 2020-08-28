package dobby.dobbyqs.backstage.bean;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class PrintGroupQuestion {
    String type;
    String addition;
    List<String> options;
    List<PrintQuestion> questions;
    Integer group;

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<PrintQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<PrintQuestion> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PrintGroupQuestion.class.getSimpleName() + "[", "]")
                .add("type='" + type + "'")
                .add("addition='" + addition + "'")
                .add("options=" + options)
                .add("questions=" + questions)
                .add("group=" + group)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrintGroupQuestion)) return false;
        PrintGroupQuestion that = (PrintGroupQuestion) o;
        return Objects.equals(getGroup(), that.getGroup());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroup());
    }
}
