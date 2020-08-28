package dobby.dobbyqs.paper.planB;

import java.util.List;

public class Context {
    Event lastEvent;
    int num1;
    int num2;
    int num0;
    int group;

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getNum0() {
        return num0;
    }

    public void setNum0(int num0) {
        this.num0 = num0;
    }

    public Event getLastEvent() {
        return lastEvent;
    }

    public void setLastEvent(Event lastEvent) {
        this.lastEvent = lastEvent;
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Context{");
        sb.append("lastEvent=").append(lastEvent);
        sb.append(", num1=").append(num1);
        sb.append(", num2=").append(num2);
        sb.append(", num0=").append(num0);
        sb.append(", group=").append(group);
        sb.append('}');
        return sb.toString();
    }
}
