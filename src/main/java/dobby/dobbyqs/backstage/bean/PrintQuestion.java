package dobby.dobbyqs.backstage.bean;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class PrintQuestion {
    String question;
    List<String> options;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PrintQuestion.class.getSimpleName() + "[", "]")
                .add("question='" + question + "'")
                .add("options=" + options)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrintQuestion)) return false;
        PrintQuestion that = (PrintQuestion) o;
        return Objects.equals(getQuestion(), that.getQuestion()) &&
                Objects.equals(getOptions(), that.getOptions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuestion(), getOptions());
    }
}
