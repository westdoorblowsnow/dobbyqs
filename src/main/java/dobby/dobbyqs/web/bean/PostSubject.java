package dobby.dobbyqs.web.bean;

public class PostSubject {
    Integer professionId;
    String name;

    @Override
    public String toString() {
        return "PostSubject{" +
                "professionId=" + professionId +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Integer professionId) {
        this.professionId = professionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PostSubject(Integer professionId, String name) {
        this.professionId = professionId;
        this.name = name;
    }

    public PostSubject() {
    }
}
