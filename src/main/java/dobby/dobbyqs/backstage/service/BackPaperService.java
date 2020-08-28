package dobby.dobbyqs.backstage.service;

import dobby.dobbyqs.backstage.bean.BackQuestion;
import dobby.dobbyqs.mybatis.pojo.Paper;
import dobby.dobbyqs.mybatis.pojo.Profession;

import java.util.List;
import java.util.Map;

public interface BackPaperService {
    Paper getPaperByIndex(Integer index);

    List<Paper> getPapersByProfessionId(Integer professionId);

    Profession getProfessionByProfessionId(Integer professionId);

    Map<String, List<BackQuestion>> getQuestionsByPaperId(Integer paperId);

    Paper getPaperById(Integer id);

    List<Paper> getAllPaper();
}
