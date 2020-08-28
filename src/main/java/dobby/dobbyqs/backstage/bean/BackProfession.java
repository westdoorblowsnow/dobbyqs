package dobby.dobbyqs.backstage.bean;

import dobby.dobbyqs.mybatis.pojo.Profession;
import dobby.dobbyqs.mybatis.pojo.Subject;

import java.util.List;

public class BackProfession {
    Profession profession;
    List<Subject> subjects;

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BackProfession{");
        sb.append("profession=").append(profession);
        sb.append(", subjects=").append(subjects);
        sb.append('}');
        return sb.toString();
    }
}
