package dobby.dobbyqs.web;

import dobby.dobbyqs.mybatis.pojo.*;
import dobby.dobbyqs.web.bean.GetProfession;
import dobby.dobbyqs.web.bean.GetQuestion;
import dobby.dobbyqs.web.bean.GetSubject;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库pojo对象转web bean对象工具类
 */
public class POJOToWebBean {

    public static GetQuestion toGetQuestion(Question question, Options options, Addition addition) {
        GetQuestion getQuestion = null;
        if (question != null) {
            getQuestion = new GetQuestion();
            getQuestion.setId(question.getId());
            getQuestion.setQuestion(question.getQuestion());
            getQuestion.setType(question.getType());
            getQuestion.setExplain(question.getExplain());
            getQuestion.setAnswer(DobbyUtils.stringAnswer(question.getAnswer()));
            getQuestion.setTag(question.getTag());
            if ("A3".equals(question.getType()))
                getQuestion.setGroup(question.getAdditionId().toString());
            else if ("B1".equals(question.getType()))
                getQuestion.setGroup(question.getOptionsId().toString());
            if (addition != null)
                getQuestion.setAddition(addition.getAddition());
            if (options != null)
                getQuestion.setOptions(DobbyUtils.listOptions(options.getOptions()));
        }
        return getQuestion;
    }

    public static GetSubject toGetSubject(Subject subject) {
        GetSubject getSubject = new GetSubject();
        getSubject.setId(subject.getId());
        getSubject.setName(subject.getName());
        return getSubject;
    }

    public static GetProfession toGetProfession(Profession profession, List<Subject> subjects) {
        GetProfession getProfession = new GetProfession();
        getProfession.setId(profession.getId());
        getProfession.setCode(profession.getCode());
        getProfession.setName(profession.getName());
        List<GetSubject> getSubjects = new ArrayList<>();
        for (Subject subject :
                subjects) {
            getSubjects.add(toGetSubject(subject));
        }
        getProfession.setSubjects(getSubjects);
        return getProfession;
    }

}
