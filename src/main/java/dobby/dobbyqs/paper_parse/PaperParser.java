package dobby.dobbyqs.paper_parse;

import dobby.dobbyqs.web.StringUtils;
import dobby.dobbyqs.web.bean.PostPaper;
import dobby.dobbyqs.web.bean.PostQuestion;

import javax.annotation.Resource;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Resource
public class PaperParser implements PaperParse {
    public static final String A1_TYPE_TITLE = "以下每一道题下面有A、B、C、D、E 五个备选答案。请从中选择一个最佳答案。";
    public static final String A2_TYPE_TITLE = "";
    public static final String A3_TYPE_TITLE = "以下提供若干个案例，每个案例下设若干个考题。请根据答案所提供的信息在每道考题下面的A、B、C、D、E 五个备选答案中选择一个最佳答案。";
    public static final String A4_TYPE_TITLE = "";
    public static final String B1_TYPE_TITLE = "以下提供若干组考题，每组考题共同在考题前列出A、B、C、D、E 五个备选答案。请从中选择一个与考题关系最密切的答案。每个备选答案可能被选择一次、多次或不被选择。";
    public static final String X_TYPE_TITLE = "";

    public static final Pattern COM_ADDITION = Pattern.compile("\\(\\d{2,3}[～~]\\d{2,3}题共用题干\\)");
    public static final Pattern COM_OPTIONS = Pattern.compile("\\(\\d{2,3}[～~]\\d{2,3}题共用备选答案\\)");
    public static final Pattern NUM = Pattern.compile("\\d{2,3}");
    public static final Pattern A = Pattern.compile("A\\.");
    public static final Pattern B = Pattern.compile("B\\.");
    public static final Pattern C = Pattern.compile("C\\.");
    public static final Pattern D = Pattern.compile("D\\.");
    public static final Pattern E = Pattern.compile("E\\.");

    public List<TowInt> numPaperPositionList = new ArrayList<>();
    public List<TowInt> numAnswerPositionList = new ArrayList<>();

    public Map<Integer, TowInt> comAdditionPosition = new HashMap<>();
    public Map<Integer, String> comAddition = new HashMap<>();
    public Map<Integer, TowInt> comOptionsPosition = new HashMap<>();
    public Map<Integer, List<String>> comOptions = new HashMap<>();
    public Map<Integer, Integer> numGroup = new HashMap<>();

    public List<Integer> APosition = new ArrayList<>();
    public List<Integer> BPosition = new ArrayList<>();
    public List<Integer> CPosition = new ArrayList<>();
    public List<Integer> DPosition = new ArrayList<>();
    public List<Integer> EPosition = new ArrayList<>();

    public PaperParser() {
    }

    public void checkNumPosition(String str, List<TowInt> towInts) {
        towInts.clear();
        int i = 0;
        int lastIndex = 0;
        int lastIndexEnd = 0;
        while (true) {
            i++;
            String num = i + ".";
            if ((lastIndex = str.indexOf(num, lastIndexEnd)) != -1) {
                lastIndexEnd = lastIndex + num.length();
//                if (towInts == numPaperPositionList)
//                    System.out.println("num:" + num + " ->" + lastIndex + " ->\n" + str.substring(0, lastIndexEnd));
                towInts.add(new TowInt(lastIndex, lastIndexEnd));
            } else {
//                if (towInts == numPaperPositionList) System.out.println("num:" + num + " ->" + lastIndex);
                break;
            }
        }
    }

    public void setComPosition(String str) {
        int k = 1;
        comAdditionPosition.clear();
        final Matcher matcher = COM_ADDITION.matcher(str);
        while (matcher.find()) {
            final TowInt com = new TowInt(matcher.start(), matcher.end());
            final String group = matcher.group();
            final Matcher matcher1 = NUM.matcher(group);
            matcher1.find();
            final int start = Integer.parseInt(matcher1.group());
            matcher1.find();
            final int end = Integer.parseInt(matcher1.group());
            if (numPaperPositionList.size() < end) return;
            final String addition = str.substring(com.getI2(), numPaperPositionList.get(start - 1).getI1()).replaceAll("\n", "");
            for (int i = start; i <= end; i++) {
                numGroup.put(i, k);
                comAdditionPosition.put(i, com);
                comAddition.put(i, addition);
            }
            k++;
        }
        k = 1;
        comOptionsPosition.clear();
        final Matcher matcher1 = COM_OPTIONS.matcher(str);
        int comOptionsSum = 0;
        while (matcher1.find()) {
            final Matcher matcher2 = NUM.matcher(matcher1.group());
            matcher2.find();
            final int start = Integer.parseInt(matcher2.group());
            matcher2.find();
            final int end = Integer.parseInt(matcher2.group());
            if (end > numPaperPositionList.size())
                throw new PaperParserException("题号缺失：" + (numPaperPositionList.size()));
//            System.out.println(start);
            int a = APosition.get(start - 1 - comOptionsSum);
            int b = BPosition.get(start - 1 - comOptionsSum);
            int c = CPosition.get(start - 1 - comOptionsSum);
            int d = DPosition.get(start - 1 - comOptionsSum);
            int e = EPosition.get(start - 1 - comOptionsSum);
            if (!(e > d && d > c && c > b && b > a)) {
                throw new PaperParserException("公用备选答案错误：" + start + "[" + a + "," + b + "," + c + "," + d + "," + e + "," + "]");
            }
//            System.out.println("start:" + start + " end:" + end + "  " + a + "," + b + "," + c + "," + d + "," + e + ",");
            List<String> options = new ArrayList<>();
            options.add(str.substring(a + 2, b).trim().replaceAll("\n", ""));
            options.add(str.substring(b + 2, c).trim().replaceAll("\n", ""));
            options.add(str.substring(c + 2, d).trim().replaceAll("\n", ""));
            options.add(str.substring(d + 2, e).trim().replaceAll("\n", ""));
            try {
                options.add(str.substring(e + 2, numPaperPositionList.get(start - 1).getI1()));
            } catch (IndexOutOfBoundsException ee) {
//                ee.printStackTrace();
                throw new PaperParserException("缺少题号：" + (numPaperPositionList.size() + 1) + "或组识别缺失：" + start + "附近");
            }
            String addition = null;
            final String s = options.get(4);
            final String[] split = s.split("\n");
            options.remove(4);
            options.add(split[0]);
            if (split.length > 1) {
                addition = split[1];
            }
            for (int i = start; i <= end; i++) {
                numGroup.put(i, k + 1000);
                comOptionsPosition.put(i, new TowInt(matcher1.start(), matcher1.end()));
                comOptions.put(i, options);
                if (addition != null) comAddition.put(i, addition.trim());
            }
            k++;
            comOptionsSum += (end - start);
        }
    }

    public void setABCDE(String str) {
        APosition.clear();
        final Matcher matcherA = A.matcher(str);
        while (matcherA.find()) APosition.add(matcherA.start());
        BPosition.clear();
        final Matcher matcherB = B.matcher(str);
        while (matcherB.find()) BPosition.add(matcherB.start());
        CPosition.clear();
        final Matcher matcherC = C.matcher(str);
        while (matcherC.find()) CPosition.add(matcherC.start());
        DPosition.clear();
        final Matcher matcherD = D.matcher(str);
        while (matcherD.find()) DPosition.add(matcherD.start());
        EPosition.clear();
        final Matcher matcherE = E.matcher(str);
        while (matcherE.find()) EPosition.add(matcherE.start());
    }

    private String isValid() {
        if (numPaperPositionList.isEmpty()) return "未解析出任何题号！";
        int questionSum = numPaperPositionList.size();
        int comOptionsSum = 0;
        int indexNum = 0;
        int indexAnswer = 0;
        int indexA = 0;
        int indexB = 0;
        int indexC = 0;
        int indexD = 0;
        int indexE = 0;
        for (int num = 1; num <= questionSum; num++) {
            indexNum = numPaperPositionList.get(num - 1).getI1();
            if (indexNum < indexE) {
                return "题号位置偏前，文本中出现非题目题号标志,或上题缺少E选项：" + num;
            }
            try {
                indexAnswer = numAnswerPositionList.get(num - 1).getI1();
            } catch (IndexOutOfBoundsException e) {
//                System.out.println(numAnswerPositionList.size());
//                e.printStackTrace();
                return "答案数量少于题目数量：" + num;
            }
            try {
                indexA = APosition.get(num - 1 - comOptionsSum);
            } catch (IndexOutOfBoundsException e) {
                return "解析出的A选项数量少：" + num;
            }
            try {
                indexB = BPosition.get(num - 1 - comOptionsSum);
            } catch (IndexOutOfBoundsException e) {
                return "解析出的B选项数量少：" + num;
            }
            try {
                indexC = CPosition.get(num - 1 - comOptionsSum);
            } catch (IndexOutOfBoundsException e) {
                return "解析出的C选项数量少：" + num;
            }
            try {
                indexD = DPosition.get(num - 1 - comOptionsSum);
            } catch (IndexOutOfBoundsException e) {
                return "解析出的D选项数量少：" + num;
            }
            try {
                indexE = EPosition.get(num - 1 - comOptionsSum);
            } catch (IndexOutOfBoundsException e) {
                return "解析出的E选项数量少：" + num;
            }
            if (numGroup.containsKey(num) && numGroup.get(num) > 1000) {
                comOptionsSum++;
                if (!(indexE > indexD && indexD > indexC && indexC > indexB && indexB > indexA && indexNum > indexE)) {
                    return "题目组题目的逻辑顺序有误：" + num;
                }
            } else {
                if (!(indexE > indexD && indexD > indexC && indexC > indexB && indexB > indexA && indexA > indexNum)) {
                    return "题目逻辑顺序有误：" + num;
                }
                if (numGroup.containsKey(num)) {
                    int comMark = comAdditionPosition.get(num).getI1();
                    if (comMark > indexNum) return "题号出现在题组标志之前：" + num;
                }
            }
        }
        return null;
    }

    private String isValidBeforeCom(String paper) {
        if (numPaperPositionList.isEmpty()) return "未解析出任何题号！";
        int questionSum = numPaperPositionList.size();
        final Matcher matcher = COM_OPTIONS.matcher(paper);
        if (matcher.find()) {
            final Matcher matcher1 = NUM.matcher(matcher.group());
            matcher1.find();
            final int i = Integer.parseInt(matcher1.group());
            if (i - 1 < questionSum) questionSum = i - 1;
        }
        final Matcher matcher1 = COM_ADDITION.matcher(paper);
        if (matcher1.find()) {
            final Matcher matcher2 = NUM.matcher(matcher1.group());
            matcher2.find();
            final int i = Integer.parseInt(matcher2.group());
            if (i - 1 < questionSum) questionSum = i - 1;
        }
        int indexNum = 0;
        int indexA = 0;
        int indexB = 0;
        int indexC = 0;
        int indexD = 0;
        int indexE = 0;
        for (int num = 1; num <= questionSum; num++) {
            indexNum = numPaperPositionList.get(num - 1).getI1();
            if (indexNum < indexE) {
                return "题号位置有误偏前，或文本中出现非题目的题号标志，或上题缺少E选项：" + num;
            }
            try {
                indexA = APosition.get(num - 1);
            } catch (IndexOutOfBoundsException e) {
                return "解析出的A选项数量少：" + num;
            }
            try {
                indexB = BPosition.get(num - 1);
            } catch (IndexOutOfBoundsException e) {
                return "解析出的B选项数量少：" + num;
            }
            try {
                indexC = CPosition.get(num - 1);
            } catch (IndexOutOfBoundsException e) {
                return "解析出的C选项数量少：" + num;
            }
            try {
                indexD = DPosition.get(num - 1);
            } catch (IndexOutOfBoundsException e) {
                return "解析出的D选项数量少：" + num;
            }
            try {
                indexE = EPosition.get(num - 1);
            } catch (IndexOutOfBoundsException e) {
                return "解析出的E选项数量少：" + num;
            }
            if (!(indexE > indexD && indexD > indexC && indexC > indexB && indexB > indexA && indexA > indexNum)) {
                return "题目逻辑顺序有误：" + num;
            }
        }
        return null;
    }


    @Override
    public PostPaper parser(String paper, String answer) {
//        System.out.println(answer);
        checkNumPosition(paper, numPaperPositionList);
        checkNumPosition(answer, numAnswerPositionList);
        setABCDE(paper);
        final String validBeforeCom = isValidBeforeCom(paper);
        if (validBeforeCom != null) throw new PaperParserException(validBeforeCom);
        setComPosition(paper);
        final String valid = isValid();
        if (valid != null) throw new PaperParserException(valid);//试卷格式验证
        PostPaper postPaper = new PostPaper();
        List<PostQuestion> postQuestions = new ArrayList<>();
        postPaper.setQuestions(postQuestions);
        for (int i = 0; i < numPaperPositionList.size(); i++) {
            postQuestions.add(new PostQuestion());
        }
        int num = 0;
        for (int i = 0; i < postQuestions.size(); i++) {
            final PostQuestion postQuestion = postQuestions.get(i);
            if (numGroup.containsKey(i + 1) && numGroup.get(i + 1) > 1000) {
                postQuestion.setQuestion(paper.substring(numPaperPositionList.get(i).getI2()).split("\n")[0]);
                postQuestion.setType("B1");
                postQuestion.setOptions(comOptions.get(i + 1));
                postQuestion.setAddition(comAddition.containsKey(i + 1) ? comAddition.get(i + 1).replaceAll("\n", "") : null);
                postQuestion.setGroup(comOptionsPosition.get(i + 1).getI1() + "");
            } else {
                List<String> options = new ArrayList<>();
                options.add(paper.substring(APosition.get(i) + 2, BPosition.get(i)).replaceAll("\n", ""));
                options.add(paper.substring(BPosition.get(i) + 2, CPosition.get(i)).replaceAll("\n", ""));
                options.add(paper.substring(CPosition.get(i) + 2, DPosition.get(i)).replaceAll("\n", ""));
                options.add(paper.substring(DPosition.get(i) + 2, EPosition.get(i)).replaceAll("\n", ""));
                options.add(paper.substring(EPosition.get(i) + 2));
                final String s = options.get(4);
                if (s.contains("\n")) {
                    options.remove(4);
                    final String[] split = s.split("\n");
                    options.add(split[0]);
                }
                postQuestion.setOptions(options);
                postQuestion.setQuestion(paper.substring(numPaperPositionList.get(i).getI2(), APosition.get(i)).replaceAll("\n", ""));
                if (numGroup.containsKey(i + 1)) {
                    postQuestion.setType("A3");
                    postQuestion.setAddition(comAddition.get(i + 1).replaceAll("\n", ""));
                    postQuestion.setGroup(comAdditionPosition.get(i + 1).getI1() + "");
                } else {
                    postQuestion.setType("A1");
                }
            }
            postQuestion.setAnswer(answer.substring(numAnswerPositionList.get(i).getI2(), numAnswerPositionList.get(i).getI2() + 1).replaceAll("\n", ""));
            if (i != numAnswerPositionList.size() - 1)
                postQuestion.setExplain(StringUtils.trimToNul(answer.substring(numAnswerPositionList.get(i).getI2() + 1, numAnswerPositionList.get(i + 1).getI1()).replaceAll("\n", "")));
            else
                postQuestion.setExplain(StringUtils.trimToNul(answer.substring(numAnswerPositionList.get(i).getI2() + 1).replaceAll("\n", "")));
            postQuestion.setNum(i + 1);
        }
        return postPaper;
    }


    public static class TowInt {
        @Override
        public String toString() {
            return "TowInt{" +
                    "i1=" + i1 +
                    ", i2=" + i2 +
                    '}';
        }

        Integer i1, i2;

        public Integer getI1() {
            return i1;
        }

        public void setI1(Integer i1) {
            this.i1 = i1;
        }

        public Integer getI2() {
            return i2;
        }

        public void setI2(Integer i2) {
            this.i2 = i2;
        }

        public TowInt() {
        }

        public TowInt(Integer i1, Integer i2) {
            this.i1 = i1;
            this.i2 = i2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TowInt)) return false;
            TowInt towInt = (TowInt) o;
            return Objects.equals(i1, towInt.i1) &&
                    Objects.equals(i2, towInt.i2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(i1, i2);
        }
    }
}
