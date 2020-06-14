package dobby.dobbyqs.web.bean;

import java.util.List;

public class PostPaper {
    Integer subjectId;
    String name;
    String tag;
    List<PostQuestion> questions;

    @Override
    public String toString() {
        return "PostPaper{" +
                "subjectId=" + subjectId +
                ", name='" + name + '\'' +
                ", tag='" + tag + '\'' +
                ", questions=" + questions +
                '}';
    }

    public PostPaper(Integer subjectId, String name, String tag, List<PostQuestion> questions) {
        this.subjectId = subjectId;
        this.name = name;
        this.tag = tag;
        this.questions = questions;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<PostQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<PostQuestion> questions) {
        this.questions = questions;
    }

    public PostPaper() {
    }
}
