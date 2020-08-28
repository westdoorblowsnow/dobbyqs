package dobby.dobbyqs.paper.planB;

import dobby.dobbyqs.paper.PaperParserException;

public class EventFinderAnswerAndExplain extends EventFinder {
    public EventFinderAnswerAndExplain(String str, Context context) {
        super(str, context);
        this.context.setLastEvent(Event.create().name(Event.STRING_START).index(0));
        if (str.indexOf("案例1") != -1 && str.indexOf("案例2") != -1 && str.indexOf("提问1") != -1) {
            this.context.getLastEvent().type(Event.TYPE_ANSWER_X);
        } else {
            this.context.getLastEvent().type(Event.TYPE_ANSWER);
        }
    }

    @Override
    public Event nextEvent() {
        index = context.getLastEvent().getIndex();
        if (context.getLastEvent().getType().equals(Event.TYPE_ANSWER)) {
            final int index = findNum(context.getNum0() + 1);
            if (context.getNum0() + 1 < context.getNum2()) {
                if (index == Integer.MAX_VALUE) throw new PaperParserException("答案缺少题号：" + (context.getNum0() + 1));
            } else {
                if (index == Integer.MAX_VALUE)
                    return Event.create().name(Event.STRING_END).type(Event.TYPE_ANSWER).index(str.length() - 1);
            }
            return Event.create().name(Event.ANSWER_NUM).type(Event.TYPE_ANSWER).index(index);
        } else if (context.getLastEvent().getType().equals(Event.TYPE_ANSWER_X)) {
            final int xNum = findXNum(context.getNum0() + 1);
            final int xExampleNum = findXExampleNum(context.getNum1() + 1);
            if (xNum < xExampleNum) {
                if (xNum == Integer.MAX_VALUE)
                    return Event.create().name(Event.STRING_END).type(Event.TYPE_ANSWER_X).index(str.length() - 1);
                else return Event.create().name(Event.ANSWER_NUM_X).type(Event.TYPE_ANSWER_X).index(xNum);
            } else {
                if (xExampleNum == Integer.MAX_VALUE)
                    return Event.create().name(Event.STRING_END).type(Event.TYPE_ANSWER_X).index(str.length() - 1);
                else return Event.create().name(Event.ANSWER_EXAMPLE_X).type(Event.TYPE_ANSWER_X).index(xExampleNum);
            }
        }
        throw new PaperParserException("答案格式有误！！");
    }


    private int findNum(int num) {
        final int i = str.indexOf(num + ".", index);
        if (i == -1) return Integer.MAX_VALUE;
        return i;
    }

    private int findXNum(int num) {
        final int i = str.indexOf("提问" + num, index);
        if (i == -1) return Integer.MAX_VALUE;
        return i;
    }

    private int findXExampleNum(int num) {
        final int i = str.indexOf("案例" + num, index);
        if (i == -1) return Integer.MAX_VALUE;
        return i;
    }
}
