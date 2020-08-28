package dobby.dobbyqs.paper.planB;

import dobby.dobbyqs.paper.PaperParserException;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventFinderQuestion extends EventFinder {
    public EventFinderQuestion(String str, Context context) {
        super(str, context);
        this.context.setLastEvent(Event.create().name(Event.STRING_START).index(0));
        this.context.setNum0(0);
        this.index = 0;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Event nextEvent() {
        index = context.getLastEvent().getIndex();
        Event event = null;
        if (context.getLastEvent().equalsAnyName(Event.STRING_START, Event.A1_E, Event.A3_ADDITION_START, Event.A3_ADDITION_END, Event.A3_E, Event.B1_OPTIONS_START, Event.B1_OPTIONS_END, Event.B1_E, Event.B1_START_NUM)) {
            event = nearest(context.getNum0() + 1, context.getNum1() + 1, context.getNum2() + 1);
        } else {
            event = nearest(context.getNum0(), context.getNum1() + 1, context.getNum2() + 1);
        }
        switch (context.getLastEvent().getName()) {
            case Event.STRING_START:
                if (!event.equalsAnyName(Event.A1_START_NUM, Event.A3_ADDITION_START, Event.B1_OPTIONS_START, Event.X_PAPER))
                    throw new PaperParserException("需要[题目起始标志或案列卷标志]，却得到[" + event.getName() + "]题号：" + context.getNum0());
                break;
            case Event.A1_START_NUM:
                if (!event.equalsName(Event.A1_A))
                    throw new PaperParserException("需要[" + Event.A1_A + "]，确得到[" + event.getName() + "]题号：" + context.getNum0());
                break;
            case Event.A1_A:
                if (!event.equalsName(Event.A1_B))
                    throw new PaperParserException("需要[" + Event.A1_B + "]，确得到[" + event.getName() + "]题号：" + context.getNum0());
                break;
            case Event.A1_B:
                if (!event.equalsName(Event.A1_C))
                    throw new PaperParserException("需要[" + Event.A1_C + "]，确得到[" + event.getName() + "]题号：" + context.getNum0());
                break;
            case Event.A1_C:
                if (!event.equalsName(Event.A1_D))
                    throw new PaperParserException("需要[" + Event.A1_D + "]，确得到[" + event.getName() + "]题号：" + context.getNum0());
                break;
            case Event.A1_D:
                if (!event.equalsName(Event.A1_E))
                    throw new PaperParserException("需要[" + Event.A1_E + "]，确得到[" + event.getName() + "]题号：" + context.getNum0());
                break;
            case Event.A1_E:
                if (!event.equalsName(Event.A1_START_NUM) && !event.equalsName(Event.A3_ADDITION_START) && !event.equalsName(Event.B1_OPTIONS_START) && !event.equalsName(Event.STRING_END))
                    throw new PaperParserException("需要[题目起始标志]，却得到[" + event.getName() + "]题号：" + context.getNum0());
                break;
            case Event.A3_ADDITION_START:
                if (!event.equalsName(Event.A3_ADDITION_END))
                    throw new PaperParserException("需要[" + Event.A3_ADDITION_END + "]，确得到[" + event.getName() + "]题号：" + context.getNum0());
                break;
            case Event.A3_ADDITION_END:
                if (!event.equalsName(Event.A3_START_NUM))
                    throw new PaperParserException("需要[" + Event.A3_START_NUM + "]，确得到[" + event.getName() + "]题号：" + context.getNum0());
                break;
            case Event.A3_START_NUM:
                if (!event.equalsName(Event.A3_A))
                    throw new PaperParserException("需要[" + Event.A3_A + "]，确得到[" + event.getName() + "]题号：" + context.getNum0());
                break;
            case Event.A3_A:
                if (!event.equalsName(Event.A3_B))
                    throw new PaperParserException("需要[" + Event.A3_B + "]，确得到[" + event.getName() + "]题号：" + context.getNum0());
                break;
            case Event.A3_B:
                if (!event.equalsName(Event.A3_C))
                    throw new PaperParserException("需要[" + Event.A3_C + "]，确得到[" + event.getName() + "]题号：" + context.getNum0());
                break;
            case Event.A3_C:
                if (!event.equalsName(Event.A3_D))
                    throw new PaperParserException("需要[" + Event.A3_D + "]，确得到[" + event.getName() + "]题号：" + context.getNum0());
                break;
            case Event.A3_D:
                if (!event.equalsName(Event.A3_E))
                    throw new PaperParserException("需要[" + Event.A3_E + "]，确得到[" + event.getName() + "]题号：" + context.getNum0());
                break;
            case Event.A3_E:
                if (context.getNum0() < context.getNum2()) {
                    if (!event.equalsName(Event.A3_START_NUM))
                        throw new PaperParserException("需要[" + Event.A3_START_NUM + "]，确得到[" + event.getName() + "]题号：" + context.getNum0());
                } else {
                    if (!event.equalsAnyName(Event.A3_ADDITION_START, Event.B1_OPTIONS_START, Event.STRING_END)) {
//                        System.out.println(context.num0+"  "+context.num2+"  "+context.num1);
                        throw new PaperParserException("需要[题目起始标志]，却得到[" + event.getName() + "]题号：" + context.getNum0());
                    }
                }
                break;
            case Event.B1_OPTIONS_START:
                if (!event.equalsName(Event.B1_OPTIONS_END))
                    throw new PaperParserException("需要[" + Event.B1_OPTIONS_END + "]，确得到[" + event.getName() + "]题号：" + context.getNum0());
                break;
            case Event.B1_OPTIONS_END:
                if (!event.equalsName(Event.B1_A))
                    throw new PaperParserException("需要[" + Event.B1_A + "]，确得到[" + event.getName() + "]题号：" + context.getNum0());
                break;
            case Event.B1_A:
                if (!event.equalsName(Event.B1_B))
                    throw new PaperParserException("需要[" + Event.B1_B + "]，确得到[" + event.getName() + "]题号：" + context.getNum0());
                break;
            case Event.B1_B:
                if (!event.equalsName(Event.B1_C))
                    throw new PaperParserException("需要[" + Event.B1_C + "]，确得到[" + event.getName() + "]题号：" + context.getNum0());
                break;
            case Event.B1_C:
                if (!event.equalsName(Event.B1_D))
                    throw new PaperParserException("需要[" + Event.B1_D + "]，确得到[" + event.getName() + "]题号：" + context.getNum0());
                break;
            case Event.B1_D:
                if (!event.equalsName(Event.B1_E))
                    throw new PaperParserException("需要[" + Event.B1_E + "]，确得到[" + event.getName() + "]题号：" + context.getNum0());
                break;
            case Event.B1_E:
                if (!event.equalsName(Event.B1_START_NUM))
                    throw new PaperParserException("需要[" + Event.B1_START_NUM + "]，确得到[" + event.getName() + "]题号：" + context.getNum0());
                break;
            case Event.B1_START_NUM:
                if (context.getNum0() < context.getNum2()) {
                    if (!event.equalsName(Event.B1_START_NUM)) {
//                        System.out.println(context.getNum1());
//                        System.out.println(context.getNum2());
//                        System.out.println(str.indexOf("62.",3779));
//                        System.out.println(str.substring(3760 + 2, 3778));
                        throw new PaperParserException("需要[" + Event.B1_START_NUM + "]，确得到[" + event.getName() + "]题号：" + context.getNum0());
                    }
                } else if (!event.equalsAnyName(Event.A3_ADDITION_START, Event.B1_OPTIONS_START, Event.STRING_END))
                    throw new PaperParserException("需要[新题目组起始标志或文档结束]，却得到[" + event.getName() + "]题号：" + context.getNum0() + ":" + event.toString() + "||" + context.toString());
                break;
            case Event.X_PAPER:
                if (!event.equalsName(Event.X_ADDITION_START)) {
                    throw new PaperParserException("需要[" + Event.X_ADDITION_START + "]，确得到[" + event.getName() + "]题号：(" + context.getNum1() + "," + context.getNum2() + ")");
                }
                break;
            case Event.X_ADDITION_START:
                if (!event.equalsName(Event.X_QUESTION_START)) {
                    throw new PaperParserException("需要[" + Event.X_QUESTION_START + "]，确得到[" + event.getName() + "]题号：(" + context.getNum1() + "," + context.getNum2() + ")");
                }
                break;
            case Event.X_QUESTION_START:
                if (!event.equalsName(Event.X_A)) {
                    throw new PaperParserException("需要[" + Event.X_A + "]，确得到[" + event.getName() + "]题号：(" + context.getNum1() + "," + context.getNum2() + ")");
                }
                break;
            case Event.X_A:
                if (!event.equalsName(Event.X_B)) {
                    throw new PaperParserException("需要[" + Event.X_B + "]，确得到[" + event.getName() + "]题号：(" + context.getNum1() + "," + context.getNum2() + ")");
                }
                break;
            case Event.X_B:
                if (!event.equalsName(Event.X_C)) {
                    throw new PaperParserException("需要[" + Event.X_C + "]，确得到[" + event.getName() + "]题号：(" + context.getNum1() + "," + context.getNum2() + ")");
                }
                break;
            case Event.X_C:
                if (!event.equalsName(Event.X_D)) {
                    throw new PaperParserException("需要[" + Event.X_D + "]，确得到[" + event.getName() + "]题号：(" + context.getNum1() + "," + context.getNum2() + ")");
                }
                break;
            case Event.X_D:
                if (!event.equalsName(Event.X_E)) {
                    throw new PaperParserException("需要[" + Event.X_E + "]，确得到[" + event.getName() + "]题号：(" + context.getNum1() + "," + context.getNum2() + ")");
                }
                break;
            case Event.X_E:
                if (!event.equalsName(Event.X_F)) {
                    throw new PaperParserException("需要[" + Event.X_F + "]，确得到[" + event.getName() + "]题号：(" + context.getNum1() + "," + context.getNum2() + ")");
                }
                break;
            case Event.X_F:
                if (!event.equalsAnyName(Event.X_G, Event.X_H, Event.X_I, Event.X_J, Event.X_K, Event.X_L, Event.X_M, Event.X_N, Event.X_O, Event.X_ADDITION_START, Event.X_QUESTION_START)) {
                    throw new PaperParserException("需要[更大的选项，或者下一个提问，或者下一个案例]，却得到[" + event.getName() + "]题号：(" + context.getNum1() + "," + context.getNum2() + ")");
                }
            default:
                if (!event.equalsAnyName(Event.X_G, Event.X_H, Event.X_I, Event.X_J, Event.X_K, Event.X_L, Event.X_M, Event.X_N, Event.X_O, Event.X_ADDITION_START, Event.X_QUESTION_START)) {
                    throw new PaperParserException("需要[更大的选项，或者下一个提问，或者下一个案例]，却得到[" + event.getName() + "]题号：(" + context.getNum1() + "," + context.getNum2() + ")");
                }
        }
        return event;
    }

    private Event nearest(int num, int xNum, int xQNum) {
        int[] near = new int[23];
        near[0] = findNum(num);
        near[1] = findA();
        near[2] = findB();
        near[3] = findC();
        near[4] = findD();
        near[5] = findE();
        near[6] = findF();
        near[7] = findG();
        near[8] = findH();
        near[9] = findI();
        near[10] = findJ();
        near[11] = findK();
        near[12] = findL();
        near[13] = findM();
        near[14] = findN();
        near[15] = findO();
        near[16] = findComAdditionStart();
        near[17] = findComAdditionEnd();
        near[18] = findComOptionsStart();
        near[19] = findComOptionsEnd();
        near[20] = findXPaper();
        near[21] = findXNum(xNum);
        near[22] = findXQNum(xQNum);
        int minIndex = 0;
        for (int i = 1; i < near.length; i++) {
            if (!Event.TYPE_X.equals(context.getLastEvent().getType())) {
                if (i >= 6 && i <= 15) continue;
            }
            if (near[i] < near[minIndex]) minIndex = i;
        }
//        System.out.println(Arrays.toString(near));
//        System.out.println(minIndex);
        if (near[minIndex] == Integer.MAX_VALUE) return Event.create().name(Event.STRING_END).index(str.length() - 1);
        final Event event = Event.create().index(near[minIndex]);
        switch (minIndex) {
            case 0:
                if (context.getLastEvent().equalsName(Event.STRING_START))
                    return event.type(Event.TYPE_A1).name(Event.A1_START_NUM);
                else if (context.getLastEvent().getName().startsWith("A1"))
                    return event.type(Event.TYPE_A1).name(Event.A1_START_NUM);
                else if (context.getLastEvent().getName().startsWith("A3"))
                    return event.type(Event.TYPE_A3).name(Event.A3_START_NUM);
                else if (context.getLastEvent().getName().startsWith("B1"))
                    return event.type(Event.TYPE_B1).name(Event.B1_START_NUM);
                break;
            case 1:
                if (context.getLastEvent().equalsName(Event.STRING_START))
                    return event.type(Event.TYPE_A1).name(Event.A1_A);
                else if (context.getLastEvent().getName().startsWith("A1"))
                    return event.type(Event.TYPE_A1).name(Event.A1_A);
                else if (context.getLastEvent().getName().startsWith("A3"))
                    return event.type(Event.TYPE_A3).name(Event.A3_A);
                else if (context.getLastEvent().getName().startsWith("B1"))
                    return event.type(Event.TYPE_B1).name(Event.B1_A);
                else if (context.getLastEvent().getName().startsWith("X"))
                    return event.type(Event.TYPE_X).name(Event.X_A);
                break;
            case 2:
                if (context.getLastEvent().equalsName(Event.STRING_START))
                    return event.type(Event.TYPE_A1).name(Event.A1_B);
                else if (context.getLastEvent().getName().startsWith("A1"))
                    return event.type(Event.TYPE_A1).name(Event.A1_B);
                else if (context.getLastEvent().getName().startsWith("A3"))
                    return event.type(Event.TYPE_A3).name(Event.A3_B);
                else if (context.getLastEvent().getName().startsWith("B1"))
                    return event.type(Event.TYPE_B1).name(Event.B1_B);
                else if (context.getLastEvent().getName().startsWith("X"))
                    return event.type(Event.TYPE_X).name(Event.X_B);
                break;
            case 3:
                if (context.getLastEvent().equalsName(Event.STRING_START))
                    return event.type(Event.TYPE_A1).name(Event.A1_C);
                else if (context.getLastEvent().getName().startsWith("A1"))
                    return event.type(Event.TYPE_A1).name(Event.A1_C);
                else if (context.getLastEvent().getName().startsWith("A3"))
                    return event.type(Event.TYPE_A3).name(Event.A3_C);
                else if (context.getLastEvent().getName().startsWith("B1"))
                    return event.type(Event.TYPE_B1).name(Event.B1_C);
                else if (context.getLastEvent().getName().startsWith("X"))
                    return event.type(Event.TYPE_X).name(Event.X_C);
                break;
            case 4:
                if (context.getLastEvent().equalsName(Event.STRING_START))
                    return event.type(Event.TYPE_A1).name(Event.A1_D);
                else if (context.getLastEvent().getName().startsWith("A1"))
                    return event.type(Event.TYPE_A1).name(Event.A1_D);
                else if (context.getLastEvent().getName().startsWith("A3"))
                    return event.type(Event.TYPE_A3).name(Event.A3_D);
                else if (context.getLastEvent().getName().startsWith("B1"))
                    return event.type(Event.TYPE_B1).name(Event.B1_D);
                else if (context.getLastEvent().getName().startsWith("X"))
                    return event.type(Event.TYPE_X).name(Event.X_D);
                break;
            case 5:
                if (context.getLastEvent().equalsName(Event.STRING_START))
                    return event.type(Event.TYPE_A1).name(Event.A1_E);
                else if (context.getLastEvent().getName().startsWith("A1"))
                    return event.type(Event.TYPE_A1).name(Event.A1_E);
                else if (context.getLastEvent().getName().startsWith("A3"))
                    return event.type(Event.TYPE_A3).name(Event.A3_E);
                else if (context.getLastEvent().getName().startsWith("B1"))
                    return event.type(Event.TYPE_B1).name(Event.B1_E);
                else if (context.getLastEvent().getName().startsWith("X"))
                    return event.type(Event.TYPE_X).name(Event.X_E);
                break;
            case 6:
                return event.type(Event.TYPE_X).name(Event.X_F);
            case 7:
                return event.type(Event.TYPE_X).name(Event.X_G);
            case 8:
                return event.type(Event.TYPE_X).name(Event.X_H);
            case 9:
                return event.type(Event.TYPE_X).name(Event.X_I);
            case 10:
                return event.type(Event.TYPE_X).name(Event.X_J);
            case 11:
                return event.type(Event.TYPE_X).name(Event.X_K);
            case 12:
                return event.type(Event.TYPE_X).name(Event.X_L);
            case 13:
                return event.type(Event.TYPE_X).name(Event.X_M);
            case 14:
                return event.type(Event.TYPE_X).name(Event.X_N);
            case 15:
                return event.type(Event.TYPE_X).name(Event.X_O);
            case 16:
                return event.type(Event.TYPE_A3).name(Event.A3_ADDITION_START);
            case 17:
                return event.type(Event.TYPE_A3).name(Event.A3_ADDITION_END);
            case 18:
                return event.type(Event.TYPE_B1).name(Event.B1_OPTIONS_START);
            case 19:
                return event.type(Event.TYPE_B1).name(Event.B1_OPTIONS_END);
            case 20:
                return event.type(Event.TYPE_X).name(Event.X_PAPER);
            case 21:
                return event.type(Event.TYPE_X).name(Event.X_ADDITION_START);
            case 22:
                return event.type(Event.TYPE_X).name(Event.X_QUESTION_START);
        }
        return null;
    }


    private int findNum(int num) {
        final int i = str.indexOf(num + ".", index + 1);
        if (i == -1) return Integer.MAX_VALUE;
        return i;
    }

    private int findA() {
        final int i = str.indexOf("A.", index + 1);
        if (i == -1) return Integer.MAX_VALUE;
        return i;
    }

    private int findB() {
        final int i = str.indexOf("B.", index + 1);
        if (i == -1) return Integer.MAX_VALUE;
        return i;
    }

    private int findC() {
        final int i = str.indexOf("C.", index + 1);
        if (i == -1) return Integer.MAX_VALUE;
        return i;
    }

    private int findD() {
        final int i = str.indexOf("D.", index + 1);
        if (i == -1) return Integer.MAX_VALUE;
        return i;
    }

    private int findE() {
        final int i = str.indexOf("E.", index + 1);
        if (i == -1) return Integer.MAX_VALUE;
        return i;
    }

    private int findF() {
        final int i = str.indexOf("F.", index + 1);
        if (i == -1) return Integer.MAX_VALUE;
        return i;
    }

    private int findG() {
        final int i = str.indexOf("G.", index + 1);
        if (i == -1) return Integer.MAX_VALUE;
        return i;
    }

    private int findH() {
        final int i = str.indexOf("H.", index + 1);
        if (i == -1) return Integer.MAX_VALUE;
        return i;
    }

    private int findI() {
        final int i = str.indexOf("I.", index + 1);
        if (i == -1) return Integer.MAX_VALUE;
        return i;
    }

    private int findJ() {
        final int i = str.indexOf("J.", index + 1);
        if (i == -1) return Integer.MAX_VALUE;
        return i;
    }

    private int findK() {
        final int i = str.indexOf("K.", index + 1);
        if (i == -1) return Integer.MAX_VALUE;
        return i;
    }

    private int findL() {
        final int i = str.indexOf("L.", index + 1);
        if (i == -1) return Integer.MAX_VALUE;
        return i;
    }

    private int findM() {
        final int i = str.indexOf("M.", index + 1);
        if (i == -1) return Integer.MAX_VALUE;
        return i;
    }

    private int findN() {
        final int i = str.indexOf("N.", index + 1);
        if (i == -1) return Integer.MAX_VALUE;
        return i;
    }

    private int findO() {
        final int i = str.indexOf("O.", index + 1);
        if (i == -1) return Integer.MAX_VALUE;
        return i;
    }

    private static final Pattern COM_ADDITION = Pattern.compile("\\(\\d{2,3}[～~-]\\d{2,3}题共用题干\\)");

    private int findComAdditionStart() {
        final Matcher matcher = COM_ADDITION.matcher(str);
        final boolean b = matcher.find(index + 1);
        if (b) return matcher.start();
        else return Integer.MAX_VALUE;
    }

    private int findComAdditionEnd() {
        final int i = str.indexOf("共用题干)", index + 1);
        if (i == -1) return Integer.MAX_VALUE;
        return i + 4;
    }

    private static final Pattern COM_OPTIONS = Pattern.compile("\\(\\d{2,3}[～~-]\\d{2,3}题共用备选答案\\)");

    private int findComOptionsStart() {
        final Matcher matcher = COM_OPTIONS.matcher(str);
        final boolean b = matcher.find(index + 1);
        if (b) return matcher.start();
        return Integer.MAX_VALUE;
    }

    private int findComOptionsEnd() {
        final int i = str.indexOf("共用备选答案)", index + 1);
        if (i == -1) return Integer.MAX_VALUE;
        return i + 6;
    }

    private int findXPaper() {
        int i = str.indexOf("案例分析题：", 0);
        if (i == -1) return Integer.MAX_VALUE;
        return i;
    }

    private int findXNum(int num) {
        int i = str.indexOf("案例" + num + ".", index + 1);
        if (i == -1) return Integer.MAX_VALUE;
        return i;
    }

    private int findXQNum(int qNum) {
        int i = str.indexOf("提问" + qNum + "：", index + 1);
        if (i == -1) return Integer.MAX_VALUE;
        return i;
    }
}
