package dobby.dobbyqs.web;

import dobby.dobbyqs.mybatis.pojo.*;
import dobby.dobbyqs.web.bean.GetProfesstion;
import dobby.dobbyqs.web.bean.GetQuestion;
import dobby.dobbyqs.web.bean.GetSubject;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库pojo对象转web bean对象工具类
 */
public class POJOToWebBean {

    public static GetQuestion toGetQuestion(Question question, Options options, Addition addition){
        GetQuestion getQuestion = new GetQuestion();
        getQuestion.setId(question.getId());
        getQuestion.setQuestion(question.getQuestion());
        getQuestion.setType(question.getType());
        getQuestion.setExplain(question.getExplain());
        getQuestion.setAddition(addition.getAddition());
        getQuestion.setOptions(DobbyUtils.listOptions(options.getOptions()));
        return getQuestion;
    }

    public static GetSubject toGetSubject(Subject subject){
        GetSubject getSubject = new GetSubject();
        getSubject.setId(subject.getId());
        getSubject.setName(subject.getName());
        return getSubject;
    }

    public static GetProfesstion toGetProfession(Profession profession, List<Subject> subjects){
        GetProfesstion getProfesstion = new GetProfesstion();
        getProfesstion.setId(profession.getId());
        getProfesstion.setCode(profession.getCode());
        getProfesstion.setName(profession.getName());
        List<GetSubject> getSubjects = new ArrayList<>();
        for (Subject subject:
             subjects) {
            getSubjects.add(toGetSubject(subject));
        }
        return getProfesstion;
    }

}
