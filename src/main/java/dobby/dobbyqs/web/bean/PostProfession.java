package dobby.dobbyqs.web.bean;

public class PostProfession {
    String code;
    String name;

    @Override
    public String toString() {
        return "PostProfession{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PostProfession(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public PostProfession() {
    }
}
