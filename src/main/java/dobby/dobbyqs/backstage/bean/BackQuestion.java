package dobby.dobbyqs.backstage.bean;

import dobby.dobbyqs.mybatis.pojo.Question;
import dobby.dobbyqs.web.DobbyUtils;

import java.util.Arrays;

public class BackQuestion {

    private Question question;

    private String[] tags;

    private String professionSubject;

    private String[] paperNames;

    private String addition;

    private String[] options;

    private String answer;

    public String[] getPaperNames() {
        return paperNames;
    }

    public void setPaperNames(String[] paperNames) {
        this.paperNames = paperNames;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public static BackQuestion create() {
        return new BackQuestion();
    }

    public BackQuestion question(Question question) {
        this.question = question;
        return this;
    }

    public BackQuestion tags(String[] tags) {
        this.tags = tags;
        return this;
    }

    public BackQuestion paperNames(String[] paperNames) {
        this.paperNames = paperNames;
        return this;
    }

    public BackQuestion professionSubject(String professionSubject) {
        this.professionSubject = professionSubject;
        return this;
    }

    public BackQuestion addition(String addition) {
        this.addition = addition;
        return this;
    }

    public BackQuestion options(String[] options) {
        this.options = options;
        return this;
    }

    public BackQuestion answer() {
        this.answer = DobbyUtils.stringAnswer(question.getAnswer());
        return this;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getProfessionSubject() {
        return professionSubject;
    }

    public void setProfessionSubject(String professionSubject) {
        this.professionSubject = professionSubject;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BackQuestion{");
        sb.append("question=").append(question);
        sb.append(", tags=").append(Arrays.toString(tags));
        sb.append(", professionSubject='").append(professionSubject).append('\'');
        sb.append(", addition='").append(addition).append('\'');
        sb.append(", options=").append(Arrays.toString(options));
        sb.append(", answer='").append(answer).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BackQuestion)) return false;

        BackQuestion that = (BackQuestion) o;

        if (getQuestion() != null ? !getQuestion().equals(that.getQuestion()) : that.getQuestion() != null)
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getTags(), that.getTags())) return false;
        if (getProfessionSubject() != null ? !getProfessionSubject().equals(that.getProfessionSubject()) : that.getProfessionSubject() != null)
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getPaperNames(), that.getPaperNames())) return false;
        if (getAddition() != null ? !getAddition().equals(that.getAddition()) : that.getAddition() != null)
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getOptions(), that.getOptions())) return false;
        return getAnswer() != null ? getAnswer().equals(that.getAnswer()) : that.getAnswer() == null;
    }

    @Override
    public int hashCode() {
        int result = getQuestion() != null ? getQuestion().hashCode() : 0;
        result = 31 * result + Arrays.hashCode(getTags());
        result = 31 * result + (getProfessionSubject() != null ? getProfessionSubject().hashCode() : 0);
        result = 31 * result + Arrays.hashCode(getPaperNames());
        result = 31 * result + (getAddition() != null ? getAddition().hashCode() : 0);
        result = 31 * result + Arrays.hashCode(getOptions());
        result = 31 * result + (getAnswer() != null ? getAnswer().hashCode() : 0);
        return result;
    }
}
