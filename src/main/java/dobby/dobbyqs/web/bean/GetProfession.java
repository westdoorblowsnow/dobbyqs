package dobby.dobbyqs.web.bean;

import dobby.dobbyqs.mybatis.pojo.Subject;

import java.util.List;

public class GetProfession {
    Integer id;
    String code;
    String name;
    List<GetSubject> subjects;

    public GetProfession() {
    }

    public GetProfession(Integer id, String code, String name, List<GetSubject> subjects) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.subjects = subjects;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubjects(List<GetSubject> subjects) {
        this.subjects = subjects;
    }

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public List<GetSubject> getSubjects() {
        return subjects;
    }

    @Override
    public String toString() {
        return "GetProfesstion{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}
