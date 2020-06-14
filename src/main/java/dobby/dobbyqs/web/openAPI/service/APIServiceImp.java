package dobby.dobbyqs.web.openAPI.service;

import dobby.dobbyqs.web.openAPI.pojo.*;
import dobby.dobbyqs.web.openAPI.mapper.APIMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class APIServiceImp implements APIService {

    @Resource
    APIMapper apiMapper;

    @Override
    public LittleQuestion getLittleQuestionById(Integer id) {
        final LittleQuestion littleQuestionById = apiMapper.getLittleQuestionById(id);
        if (littleQuestionById == null) return null;
        return littleQuestionById.init();
    }

    @Override
    public AnswerAndExplain getAnswerAndExplain(Integer questionId) {
        final AnswerAndExplain answerAndExplain = apiMapper.getAnswerAndExplain(questionId);
        if (answerAndExplain == null) return null;
        System.out.println(answerAndExplain);
        answerAndExplain.setQuestionId(questionId);
        return answerAndExplain.init();

    }

    @Override
    public List<ProfessionCom> getProfessionList() {
        return null;
    }

    @Override
    public List<SubjectCom> getSubjectListByProfessionId(Integer professionId) {
        return null;
    }

    @Override
    public List<Integer> getQuestionIdListBySubjectId(Integer subjectId) {
        return null;
    }

    @Override
    public List<Integer> getQuestionIdListByProfessionId(Integer professionId) {
        return null;
    }

    @Override
    public LittlePaper getLittlePaperById(Integer id) {
        return null;
    }

    @Override
    public List<Integer> getPaperIdListBySubjectId(Integer subjectId) {
        return null;
    }

    @Override
    public List<Integer> getPaperIdListByProfessionId(Integer professionId) {
        return null;
    }

    @Override
    public List<Integer> getQuestionIdListByPaperId(Integer paperId) {
        return null;
    }

    @Override
    public String getImgByQuestionIdAndNum(Integer questionId, Integer num) {
        return null;
    }


}
