package dobby.dobbyqs.mybatis.service;

import dobby.dobbyqs.mybatis.pojo.PaperQuestionLink;
public interface PaperQuestionLinkService{


    int deleteByPrimaryKey(Integer paperId,Integer questionId);

    int insert(PaperQuestionLink record);

    int insertSelective(PaperQuestionLink record);

    PaperQuestionLink selectByPrimaryKey(Integer paperId,Integer questionId);

    int updateByPrimaryKeySelective(PaperQuestionLink record);

    int updateByPrimaryKey(PaperQuestionLink record);

}
