package dobby.dobbyqs.mybatis.service;

import dobby.dobbyqs.mybatis.pojo.PaperQuestionLink;

import java.util.List;

public interface PaperQuestionLinkService {


    int deleteByPrimaryKey(Integer paperId, Integer questionId);

    int insert(PaperQuestionLink record);

    int insertSelective(PaperQuestionLink record);

    PaperQuestionLink selectByPrimaryKey(Integer paperId, Integer questionId);

    int updateByPrimaryKeySelective(PaperQuestionLink record);

    int updateByPrimaryKey(PaperQuestionLink record);

    List<Integer> getQuestionsIdsByPaperId(Integer paperId);

}
