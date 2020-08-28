package dobby.dobbyqs.backstage.bean;

import java.util.Objects;
import java.util.StringJoiner;

public class PrintAnswer {
    String answer;
    String explain;

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

    @Override
    public String toString() {
        return new StringJoiner(", ", PrintAnswer.class.getSimpleName() + "[", "]")
                .add("answer='" + answer + "'")
                .add("explain='" + explain + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrintAnswer)) return false;
        PrintAnswer that = (PrintAnswer) o;
        return Objects.equals(getAnswer(), that.getAnswer()) &&
                Objects.equals(getExplain(), that.getExplain());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAnswer(), getExplain());
    }
}
