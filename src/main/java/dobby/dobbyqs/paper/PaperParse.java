package dobby.dobbyqs.paper;

import dobby.dobbyqs.web.bean.PostPaper;

public interface PaperParse {
    public static final String A1_TYPE_TITLE = "以下每一道题下面有A、B、C、D、E 五个备选答案。请从中选择一个最佳答案。";
    public static final String A2_TYPE_TITLE = "";
    public static final String A3_TYPE_TITLE = "以下提供若干个案例，每个案例下设若干个考题。请根据答案所提供的信息在每道考题下面的A、B、C、D、E 五个备选答案中选择一个最佳答案。";
    public static final String A4_TYPE_TITLE = "";
    public static final String B1_TYPE_TITLE = "以下提供若干组考题，每组考题共同在考题前列出A、B、C、D、E 五个备选答案。请从中选择一个与考题关系最密切的答案。每个备选答案可能被选择一次、多次或不被选择。";
    public static final String X_TYPE_TITLE = "";
    PostPaper parse(String paper, String answer);
}
