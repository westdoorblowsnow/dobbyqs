package dobby.dobbyqs.mybatis.service;

import dobby.dobbyqs.mybatis.pojo.Question;
import dobby.dobbyqs.web.bean.GetProfesstion;
import dobby.dobbyqs.web.bean.GetQuestion;

import java.util.List;

public interface QuestionService{


    int deleteByPrimaryKey(Integer id);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

    GetQuestion selectAllGet();

}
