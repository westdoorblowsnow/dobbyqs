package dobby.dobbyqs.web.openAPI.service;

import dobby.dobbyqs.web.openAPI.pojo.*;

import java.util.List;

public interface APIService {
    public LittleQuestion getLittleQuestionById(Integer id);

    public AnswerAndExplain getAnswerAndExplain(Integer questionId);

    public List<ProfessionCom> getProfessionList();

    public List<SubjectCom> getSubjectListByProfessionId(Integer professionId);

    public List<Integer> getQuestionIdListBySubjectId(Integer subjectId);

    public List<Integer> getQuestionIdListByProfessionId(Integer professionId);

    public LittlePaper getLittlePaperById(Integer id);

    public List<Integer> getPaperIdListBySubjectId(Integer subjectId);

    public List<Integer> getPaperIdListByProfessionId(Integer professionId);

    public List<Integer> getQuestionIdListByPaperId(Integer paperId);

    public String getImgByQuestionIdAndNum(Integer questionId,Integer num);
}
