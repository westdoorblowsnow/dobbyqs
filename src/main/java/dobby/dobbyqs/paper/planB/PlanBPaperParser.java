package dobby.dobbyqs.paper.planB;

import dobby.dobbyqs.paper.PaperParse;
import dobby.dobbyqs.paper.PaperParserException;
import dobby.dobbyqs.web.bean.PostPaper;
import dobby.dobbyqs.web.bean.PostQuestion;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlanBPaperParser implements PaperParse {
    public static Pattern NUM = Pattern.compile("\\d{2,3}");

    @Override
    public PostPaper parse(String paper, String answer) {
        final List<PostQuestion> questions = getQuestions(paper);
        setAnswerAndExplain(answer, questions);
        PostPaper postPaper = new PostPaper();
        postPaper.setQuestions(questions);
        return postPaper;
    }

    private List<PostQuestion> getQuestions(String paper) {
        List<PostQuestion> postQuestions = new ArrayList<>(128);
        Context context = new Context();
        EventFinder finder = new EventFinderQuestion(paper, context);
        PostQuestion postQuestion = null;
        boolean loop = true;
        while (loop) {
            final Event event = finder.nextEvent();
            //---------------------------------------------
//            System.out.println(event.getName());
            //---------------------------------------------
            switch (event.getName()) {
                case Event.A1_START_NUM:
                    if (context.getLastEvent().equalsName(Event.STRING_START)) {
                        postQuestion = new PostQuestion();
                        postQuestion.setOptions(new ArrayList<>(5));
                    } else if (context.getLastEvent().equalsName(Event.A1_E)) {
                        String e = paper.substring(context.getLastEvent().getIndex() + 2, event.getIndex()).trim().replaceAll("\n", "");
                        postQuestion.getOptions().add(e);
                        postQuestions.add(postQuestion);
                        postQuestion = new PostQuestion();
                        postQuestion.setOptions(new ArrayList<>(5));
                    }
                    context.setNum0(context.getNum0() + 1);
                    postQuestion.setNum(context.getNum0());
                    postQuestion.setType("A1");
                    break;
                case Event.A1_A:
                    String q = null;
                    if (context.num0 < 10) {
                        q = paper.substring(context.getLastEvent().getIndex() + 2, event.getIndex()).trim().replaceAll("\n", "");
                    } else if (context.num0 < 100) {
                        q = paper.substring(context.getLastEvent().getIndex() + 3, event.getIndex()).trim().replaceAll("\n", "");
                    } else {
                        q = paper.substring(context.getLastEvent().getIndex() + 4, event.getIndex()).trim().replaceAll("\n", "");
                    }
                    postQuestion.setQuestion(q);
                    break;
                case Event.A1_B:
                case Event.A1_C:
                case Event.A1_D:
                case Event.A1_E:
                    String d = paper.substring(context.getLastEvent().getIndex() + 2, event.getIndex()).trim().replaceAll("\n", "");
                    postQuestion.getOptions().add(d);
                    break;
                case Event.A3_ADDITION_START:
                    if (context.getLastEvent().equalsAnyName(Event.A3_E, Event.A1_E)) {
                        String e = null;
                        if (context.getLastEvent().equalsName(Event.A1_E)) {
                            e = paper.substring(context.getLastEvent().getIndex() + 2, event.getIndex()).trim().split("\n")[0];
                        } else {
                            e = paper.substring(context.getLastEvent().getIndex() + 2, event.getIndex()).trim().replaceAll("\n", "");
                        }
                        postQuestion.getOptions().add(e);
                    } else if (context.getLastEvent().equalsName(Event.B1_START_NUM)) {
                        String qq = null;
                        if (context.getNum0() < 10) {
                            qq = paper.substring(context.getLastEvent().getIndex() + 2, event.getIndex()).trim().split("\n")[0];
                        } else if (context.getNum0() < 100) {
                            qq = paper.substring(context.getLastEvent().getIndex() + 3, event.getIndex()).trim().split("\n")[0];
                        } else {
                            qq = paper.substring(context.getLastEvent().getIndex() + 4, event.getIndex()).trim().split("\n")[0];
                        }
                        postQuestion.setQuestion(qq);
                    }
                    postQuestions.add(postQuestion);
                    break;
                case Event.A3_ADDITION_END:
                    final String substring = paper.substring(context.getLastEvent().getIndex(), event.getIndex());
                    final Matcher matcher = NUM.matcher(substring);
                    matcher.find();
                    final int num1 = Integer.parseInt(matcher.group());
                    if (num1 != context.getNum0() + 1)
                        throw new PaperParserException("共用题干题号错误！！错误题号在第 <" + context.getNum0() + "> 题后！");
                    context.setNum1(num1);
                    matcher.find();
                    final int num2 = Integer.parseInt(matcher.group());
                    context.setNum2(num2);
                    context.setGroup(context.getLastEvent().getIndex());
                    break;
                case Event.A3_START_NUM:
                    if (context.getLastEvent().equalsName(Event.A3_ADDITION_END)) {
                        postQuestion = new PostQuestion();
                        String addition = paper.substring(context.getLastEvent().getIndex() + 1, event.getIndex()).trim().replaceAll("\n", "");
                        if (addition.length() < 8)
                            throw new PaperParserException("共用题干题缺少题干！！位置： <" + context.getNum1() + "~" + context.getNum2() + "> 题");
                        postQuestion.setAddition(addition);
                    } else {
                        String ee = paper.substring(context.getLastEvent().getIndex() + 2, event.getIndex()).trim().replaceAll("\n", "");
                        postQuestion.getOptions().add(ee);
                        postQuestions.add(postQuestion);
                        PostQuestion postQuestion1 = new PostQuestion();
                        postQuestion1.setAddition(postQuestion.getAddition());
                        postQuestion = postQuestion1;
                    }
                    context.setNum0(context.getNum0() + 1);
                    postQuestion.setNum(context.getNum0());
                    postQuestion.setType("A3");
                    postQuestion.setGroup(context.getGroup() + "");
                    postQuestion.setOptions(new ArrayList<>(5));
                    break;
                case Event.A3_A:
                    String question = null;
                    if (context.getNum0() < 10) {
                        question = paper.substring(context.getLastEvent().getIndex() + 2, event.getIndex()).trim().replaceAll("\n", "");
                    } else if (context.getNum0() < 100) {
                        question = paper.substring(context.getLastEvent().getIndex() + 3, event.getIndex()).trim().replaceAll("\n", "");
                    } else {
                        question = paper.substring(context.getLastEvent().getIndex() + 4, event.getIndex()).trim().replaceAll("\n", "");
                    }
                    postQuestion.setQuestion(question);
                    break;
                case Event.A3_B:
                case Event.A3_C:
                case Event.A3_D:
                case Event.A3_E:
                    String op = paper.substring(context.getLastEvent().getIndex() + 2, event.getIndex()).trim().replaceAll("\n", "");
                    postQuestion.getOptions().add(op);
                    break;
                case Event.B1_OPTIONS_START:
                    if (context.getLastEvent().equalsAnyName(Event.A1_E, Event.A3_E)) {
                        String ee = paper.substring(context.getLastEvent().getIndex() + 2, event.getIndex()).trim().split("\n")[0];
                        postQuestion.getOptions().add(ee);
                    } else if (context.getLastEvent().equalsName(Event.B1_START_NUM)) {
                        String questionq = null;
                        if (context.getNum0() < 10) {
                            questionq = paper.substring(context.getLastEvent().getIndex() + 2, event.getIndex()).trim().replaceAll("\n", "");
                        } else if (context.getNum0() < 100) {
                            questionq = paper.substring(context.getLastEvent().getIndex() + 3, event.getIndex()).trim().replaceAll("\n", "");
                        } else {
                            questionq = paper.substring(context.getLastEvent().getIndex() + 4, event.getIndex()).trim().replaceAll("\n", "");
                        }
                        postQuestion.setQuestion(questionq);
                    }
                    postQuestions.add(postQuestion);
                    break;
                case Event.B1_OPTIONS_END:
                    final String substring1 = paper.substring(context.getLastEvent().getIndex(), event.getIndex());
                    final Matcher matcher1 = NUM.matcher(substring1);
                    matcher1.find();
                    final int num11 = Integer.parseInt(matcher1.group());
                    if (num11 != context.getNum0() + 1) throw new PaperParserException("共用选项题号错误！！错误在：" + context
                            .getNum0() + "题之后");
                    context.setNum1(num11);
                    matcher1.find();
                    final int num22 = Integer.parseInt(matcher1.group());
                    context.setNum2(num22);
                    context.setGroup(context.getLastEvent().getIndex());
                    break;
                case Event.B1_A:
//                    context.setNum0(context.getNum0() + 1);
                    postQuestion = new PostQuestion();
                    postQuestion.setOptions(new ArrayList<>(5));
                    postQuestion.setNum(context.getNum0());
                    postQuestion.setType("B1");
                    postQuestion.setGroup(context.getGroup() + "");
                    if (event.getIndex() - context.getLastEvent().getIndex() > 3) {
                        final String replaceAll = paper.substring(context.getLastEvent().getIndex() + 1, event.getIndex()).trim().replaceAll("\n", "");
                        if (replaceAll.length() >= 5) {
                            postQuestion.setAddition(replaceAll);
                        }
                    }
                    break;
                case Event.B1_B:
                case Event.B1_C:
                case Event.B1_D:
                case Event.B1_E:
                    String ddd = paper.substring(context.getLastEvent().getIndex() + 2, event.getIndex()).trim().replaceAll("\n", "");
                    postQuestion.getOptions().add(ddd);
                    break;
                case Event.B1_START_NUM:
                    context.setNum0(context.getNum0() + 1);
                    if (context.getLastEvent().equalsName(Event.B1_E)) {
                        postQuestion.setNum(context.getNum0());
                        final String substring2 = paper.substring(context.getLastEvent().getIndex() + 2, event.getIndex()).trim();
                        final String[] split = substring2.split("\n");
                        String eee = split[0];
                        postQuestion.getOptions().add(eee);
                        if (split.length > 1) {
                            String add = "";
                            for (int i = 1; i < split.length; i++) {
                                add += split[i];
                            }
                            postQuestion.setAddition(add.trim().replaceAll("\n", ""));
                        }
                    } else if (context.getLastEvent().equalsName(Event.B1_START_NUM)) {
                        String qusetionqq = null;
                        if (context.getNum0() < 10) {
                            qusetionqq = paper.substring(context.getLastEvent().getIndex() + 2, event.getIndex()).trim().replaceAll("\n", "");
                        } else if (context.getNum0() < 100) {
//                            System.out.println(event);
                            qusetionqq = paper.substring(context.getLastEvent().getIndex() + 3, event.getIndex()).trim().replaceAll("\n", "");
                        } else {
                            qusetionqq = paper.substring(context.getLastEvent().getIndex() + 4, event.getIndex()).trim().replaceAll("\n", "");
                        }
                        postQuestion.setQuestion(qusetionqq);
                        postQuestions.add(postQuestion);

                        PostQuestion postQuestion1 = new PostQuestion();
                        postQuestion1.setType("B1");
                        postQuestion1.setNum(context.getNum0());
                        postQuestion1.setGroup(context.getGroup() + "");
                        postQuestion1.setOptions(postQuestion.getOptions());
                        postQuestion1.setAddition(postQuestion.getAddition());
                        postQuestion = postQuestion1;
                    }
                    break;
                case Event.X_PAPER:
                    context.setNum1(0);
                    context.setNum2(0);
                    break;
                case Event.X_ADDITION_START:
                    if (context.getLastEvent().equalsName(Event.X_PAPER)) {
                    } else if (context.getLastEvent().equalsAnyName(Event.X_F, Event.X_G, Event.X_H, Event.X_I, Event.X_J, Event.X_K, Event.X_L, Event.X_M, Event.X_N, Event.X_O)) {
                        String substring2 = paper.substring(context.getLastEvent().getIndex() + 2, event.getIndex());
                        postQuestion.getOptions().add(substring2);
                        postQuestions.add(postQuestion);
                    }
                    context.setNum1(context.getNum1() + 1);
                    context.setGroup(event.getIndex());
                    context.setNum2(0);
                    break;
                case Event.X_QUESTION_START:
                    if (context.getLastEvent().equalsName(Event.X_ADDITION_START)) {
                        String substring2 = paper.substring(context.getLastEvent().getIndex(), event.getIndex()).trim().replaceAll("\n", "").replaceAll("案例\\d{1,2}\\.", "");

                        postQuestion = new PostQuestion();
                        postQuestion.setAddition(substring2);
                        postQuestion.setType("X");
                        postQuestion.setGroup(context.getGroup() + "");
                    } else if (context.getLastEvent().equalsAnyName(Event.X_F, Event.X_G, Event.X_H, Event.X_I, Event.X_J, Event.X_K, Event.X_L, Event.X_M, Event.X_N, Event.X_O)) {
                        String s = paper.substring(context.getLastEvent().getIndex() + 2, event.getIndex()).trim().replaceAll("\n", "");
                        postQuestion.getOptions().add(s);
                        postQuestions.add(postQuestion);

                        PostQuestion postQuestion2 = new PostQuestion();
                        postQuestion2.setAddition(postQuestion.getAddition());
                        postQuestion2.setType("X");
                        postQuestion2.setGroup(postQuestion.getGroup());
                        postQuestion = postQuestion2;
                    }
                    context.setNum2(context.getNum2() + 1);
                    break;
                case Event.X_A:
                    if (context.getLastEvent().equalsName(Event.X_QUESTION_START)) {
                        String s = paper.substring(context.getLastEvent().getIndex(), event.getIndex()).trim().replaceAll("\n", "").replaceAll("提问\\d{1,2}:", "");
                        postQuestion.setQuestion(s);
                    }
                case Event.X_B:
                case Event.X_C:
                case Event.X_D:
                case Event.X_E:
                case Event.X_F:
                case Event.X_G:
                case Event.X_I:
                case Event.X_J:
                case Event.X_K:
                case Event.X_L:
                case Event.X_M:
                case Event.X_N:
                case Event.X_O:
                    String substring3 = paper.substring(context.getLastEvent().getIndex() + 2, event.getIndex()).trim().replaceAll("\n", "");
                    postQuestion.getOptions().add(substring3);
                    postQuestions.add(postQuestion);
                    break;
                case Event.STRING_END:
                    if (context.getLastEvent().equalsName(Event.B1_START_NUM)) {
                        String question111 = null;
                        if (context.getNum0() < 10) {
                            question111 = paper.substring(context.getLastEvent().getIndex() + 2).trim().replaceAll("\n", "");
                        } else if (context.getNum0() < 100) {
                            question111 = paper.substring(context.getLastEvent().getIndex() + 3).trim().replaceAll("\n", "");
                        } else {
                            question111 = paper.substring(context.getLastEvent().getIndex() + 4).trim().replaceAll("\n", "");
                        }
                        postQuestion.setQuestion(question111);
                        postQuestions.add(postQuestion);
                    } else if (context.getLastEvent().equalsName(Event.A3_E)) {
                        final String eeeee = paper.substring(context.getLastEvent().getIndex() + 2);
                        postQuestion.getOptions().add(eeeee);
                        postQuestions.add(postQuestion);
                    } else if (context.getLastEvent().equalsName(Event.A1_E)) {
                        final String eeeee3 = paper.substring(context.getLastEvent().getIndex() + 2);
                        postQuestion.getOptions().add(eeeee3);
                        postQuestions.add(postQuestion);
                    } else if (context.getLastEvent().equalsAnyName(Event.X_F, Event.X_G, Event.X_H, Event.X_I, Event.X_J, Event.X_K, Event.X_L, Event.X_M, Event.X_N, Event.X_O)) {
                        String substring2 = paper.substring(context.getLastEvent().getIndex() + 2).replaceAll("\n", "").trim();
                        postQuestion.getOptions().add(substring2);
                        postQuestions.add(postQuestion);
                    }
                    loop = false;
                    break;
            }
            System.out.println(event);
            context.setLastEvent(event);
        }
        return postQuestions;
    }

    private List<PostQuestion> setAnswerAndExplain(String answer, List<PostQuestion> postQuestions) {
        Context context = new Context();
        EventFinder finder = new EventFinderAnswerAndExplain(answer, context);
        if (context.getLastEvent().getType().equals(Event.TYPE_ANSWER)) {
            context.setNum2(postQuestions.size());
            boolean b = true;
            while (b) {
                final Event event = finder.nextEvent();
                if (context.getLastEvent().equalsName(Event.STRING_START)) {
                    if (event.equalsName(Event.ANSWER_NUM)) {
                        context.setNum0(context.getNum0() + 1);
                    }
                } else if (context.getLastEvent().equalsName(Event.ANSWER_NUM)) {
                    String ae = null;
                    if (event.equalsName(Event.ANSWER_NUM)) {
//                        System.out.println(event.toString() + context.getNum0());
                        if (context.getNum0() < 10) {
                            ae = answer.substring(context.getLastEvent().getIndex() + 2, event.getIndex());
                        } else if (context.getNum0() < 100) {
                            ae = answer.substring(context.getLastEvent().getIndex() + 3, event.getIndex());
                        } else {
                            ae = answer.substring(context.getLastEvent().getIndex() + 4, event.getIndex());
                        }
                        postQuestions.get(context.getNum0() - 1).setAnswer(ae.substring(0, 1));
                        postQuestions.get(context.getNum0() - 1).setExplain(ae.substring(1).trim().replaceAll("\n", ""));
                        context.setNum0(context.getNum0() + 1);
                    } else if (event.equalsName(Event.STRING_END)) {
//                        System.out.println(event.toString() + context.getNum0());
                        if (context.getNum0() < 10) {
                            ae = answer.substring(context.getLastEvent().getIndex() + 2);
                        } else if (context.getNum0() < 100) {
                            ae = answer.substring(context.getLastEvent().getIndex() + 3);
                        } else {
                            ae = answer.substring(context.getLastEvent().getIndex() + 4);
                        }

                        postQuestions.get(context.getNum0() - 1).setAnswer(ae.substring(0, 1));
                        postQuestions.get(context.getNum0() - 1).setExplain(ae.substring(1).trim().replaceAll("\n", ""));
                        context.setNum0(context.getNum0() + 1);
                        b = false;
                    }
//                    if (context.getLastEvent().equalsName(Event.ANSWER_NUM)){
//                        System.out.println(postQuestions.get(context.getNum0()-2).toStrLine());
////                        System.out.println("==========================================================");
//                    }
                }
                context.setLastEvent(event);
            }
        } else if (context.getLastEvent().getType().equals(Event.TYPE_ANSWER_X)) {

        }
        return postQuestions;
    }
}
