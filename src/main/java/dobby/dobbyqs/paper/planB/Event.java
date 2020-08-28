package dobby.dobbyqs.paper.planB;

import java.util.Objects;

/**
 * 试卷文档识别事件
 */
public class Event {
    //A1类型题目
    public static final String A1_START_NUM = "A1_START_NUM";
    public static final String A1_A = "A1_A";
    public static final String A1_B = "A1_B";
    public static final String A1_C = "A1_C";
    public static final String A1_D = "A1_D";
    public static final String A1_E = "A1_E";
    //A3类型题目
    public static final String A3_ADDITION_START = "A3_ADDITION_START";
    public static final String A3_ADDITION_END = "A3_ADDITION_END";
    public static final String A3_START_NUM = "A3_START_NUM";
    public static final String A3_A = "A3_A";
    public static final String A3_B = "A3_B";
    public static final String A3_C = "A3_C";
    public static final String A3_D = "A3_D";
    public static final String A3_E = "A3_E";
    //B1类型题目
    public static final String B1_OPTIONS_START = "B1_OPTIONS_START";
    public static final String B1_OPTIONS_END = "B1_OPTIONS_END";
    public static final String B1_A = "B1_A";
    public static final String B1_B = "B1_B";
    public static final String B1_C = "B1_C";
    public static final String B1_D = "B1_D";
    public static final String B1_E = "B1_E";
    public static final String B1_START_NUM = "B1_START_NUM";
    //X类型题目
    public static final String X_PAPER = "X_PAPER";
    public static final String X_ADDITION_START = "X_ADDITION_START";
    public static final String X_QUESTION_START = "X_QUESTION_START";
    public static final String X_A = "X_A";
    public static final String X_B = "X_B";
    public static final String X_C = "X_C";
    public static final String X_D = "X_D";
    public static final String X_E = "X_E";
    public static final String X_F = "X_F";
    public static final String X_G = "X_G";
    public static final String X_H = "X_H";
    public static final String X_I = "X_I";
    public static final String X_J = "X_J";
    public static final String X_K = "X_K";
    public static final String X_L = "X_L";
    public static final String X_M = "X_M";
    public static final String X_N = "X_N";
    public static final String X_O = "X_O";
    //答案类型
    public static final String ANSWER_NUM = "ANSWER_NUM";
//    public static final String ANSWER_ANSWER = "ANSWER_ANSWER";
//    public static final String ANSWER_EXPLAIN = "ANSWER_EXPLAIN";

    public static final String ANSWER_EXAMPLE_X = "ANSWER_EXAMPLE_X";
    public static final String ANSWER_NUM_X = "ANSWER_NUM_X";
//    public static final String ANSWER_EXPLAIN_X = "ANSWER_EXPLAIN_X";

    //文档开始/结束事件
    public static final String STRING_START = "STRING_START";
    public static final String STRING_END = "STRING_END";

    //事件所属类型
    public static final String TYPE_A1 = "TYPE_A1";
    public static final String TYPE_A3 = "TYPE_A3";
    public static final String TYPE_B1 = "TYPE_B1";
    public static final String TYPE_X = "TYPE_X";
    public static final String TYPE_ANSWER = "TYPE_ANSWER";
    public static final String TYPE_ANSWER_X = "TYPE_ANSWER_X";

    String name;
    String type;
    int index;

    public String getName() {
        return name;
    }

    public Event name(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public Event type(String type) {
        this.type = type;
        return this;
    }

    public int getIndex() {
        return index;
    }

    public Event index(int index) {
        this.index = index;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return Objects.equals(getName(), event.getName()) &&
                Objects.equals(getType(), event.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getType());
    }

    public boolean equalsName(String name) {
        if (name == null) return false;
        return name.equals(this.name);
    }

    public boolean equalsAnyName(String... names) {
        for (String name : names) {
            if (this.name.equals(name))
                return true;
        }
        return false;
    }

    public static Event create() {
        return new Event();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Event{");
        sb.append("name='").append(name).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", index=").append(index);
        sb.append('}');
        return sb.toString();
    }
}
