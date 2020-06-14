package dobby.dobbyqs.web.bean;

import java.util.List;

public class GetPaper {
    Integer id;
    Integer subjectId;
    String profession;

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    String subject;
    String name;
    String tag;
    List<GetQuestion> questions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "GetPaper{" +
                "id=" + id +
                ", subjectId=" + subjectId +
                ", name='" + name + '\'' +
                ", tag='" + tag + '\'' +
                ", questions=" + questions +
                '}';
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<GetQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<GetQuestion> questions) {
        this.questions = questions;
    }

    public GetPaper(Integer id, Integer subjectId, String name, String tag, List<GetQuestion> questions) {
        this.id = id;
        this.subjectId = subjectId;
        this.name = name;
        this.tag = tag;
        this.questions = questions;
    }

    public GetPaper() {
    }
}
