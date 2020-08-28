package dobby.dobbyqs.web;

import dobby.dobbyqs.mybatis.pojo.*;
import dobby.dobbyqs.web.bean.PostPaper;
import dobby.dobbyqs.web.bean.PostProfession;
import dobby.dobbyqs.web.bean.PostQuestion;
import dobby.dobbyqs.web.bean.PostSubject;

import java.util.List;

public class WebBeanToPOJO {
    public static Question toQuestion(PostQuestion postQuestion, Integer additionId, Integer optionsId) {
        Question question = null;
        if (postQuestion != null) {
            question = new Question();
            question.setType(postQuestion.getType());
            question.setQuestion(postQuestion.getQuestion());
            question.setAnswer(DobbyUtils.intAnswer(postQuestion.getAnswer()));
            if (StringUtils.isBlank(postQuestion.getExplain())){
                postQuestion.setExplain("略。");
            }
            question.setExplain(postQuestion.getExplain());
            if (!StringUtils.isBlank(postQuestion.getTag()))
                question.setTag(StringUtils.trimToNul(postQuestion.getTag()));
            question.setSubjectId(postQuestion.getSubjectId());
            question.setAdditionId(additionId);
            question.setOptionsId(optionsId);
        }
        return question;
    }

    public static Paper toPaper(PostPaper postPaper) {
        Paper paper = null;
        if (postPaper != null) {
            paper = new Paper();
            paper.setName(postPaper.getName());
            paper.setSubjectId(postPaper.getSubjectId());
                paper.setTag(StringUtils.trimToNul(postPaper.getTag()));
        }
        return paper;
    }

    public static PaperQuestionLink toPaperQuestionLink(Integer paperId, Integer questionId, Integer score) {
        PaperQuestionLink paperQuestionLink = new PaperQuestionLink();
        paperQuestionLink.setPaperId(paperId);
        paperQuestionLink.setQuestionId(questionId);
        paperQuestionLink.setScore(score);
        return paperQuestionLink;
    }

    public static Addition toAddition(String addition) {
        final Addition addition1 = new Addition();
        addition1.setAddition(addition);
        return addition1;
    }

    public static Options toOptions(List<String> options) {
        final Options options1 = new Options();
        options1.setOptions(DobbyUtils.stringOptions(options));
        return options1;
    }

    public static Profession toProfession(PostProfession postProfession) {
        return new Profession(null, postProfession.getCode(), postProfession.getName());
    }

    public static Subject toSubject(PostSubject postSubject) {
        return new Subject(null, postSubject.getProfessionId(), postSubject.getName());
    }
}
