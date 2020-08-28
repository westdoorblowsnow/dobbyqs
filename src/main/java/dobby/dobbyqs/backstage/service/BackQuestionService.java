package dobby.dobbyqs.backstage.service;

import dobby.dobbyqs.backstage.bean.BackQuestion;
import dobby.dobbyqs.mybatis.pojo.Addition;
import dobby.dobbyqs.mybatis.pojo.Options;
import dobby.dobbyqs.mybatis.pojo.Question;

import java.util.List;

public interface BackQuestionService {
    List<BackQuestion> getQuestionByPage(Integer index, Integer size);

    Integer getMaxPage(Integer size);

    int updateByQuestionSelective(Question question, Addition addition, Options options);

    BackQuestion getQuestionById(Integer id);

    BackQuestion getQuestionByNearestId(Integer id,Integer move);
}
