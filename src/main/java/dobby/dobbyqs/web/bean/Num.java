package dobby.dobbyqs.web.bean;

public class Num {
    Integer num;

    public Num() {
        num = 0;
    }

    public Integer add1(){
        return ++num;
    }

    public Integer getNum() {
        return num;
    }
}
