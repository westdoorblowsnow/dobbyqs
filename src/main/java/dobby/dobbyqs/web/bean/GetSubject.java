package dobby.dobbyqs.web.bean;

public class GetSubject {
    int id;
    String name;

    public GetSubject() {
    }

    public GetSubject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
