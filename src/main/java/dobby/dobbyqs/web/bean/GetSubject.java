package dobby.dobbyqs.web.bean;

public class GetSubject {
    Integer id;
    String name;

    public GetSubject() {
    }

    public GetSubject(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GetSubject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
