package dobby.dobbyqs.web.openAPI.mapper;

import dobby.dobbyqs.web.openAPI.pojo.AnswerAndExplain;
import dobby.dobbyqs.web.openAPI.pojo.LittleQuestion;
import org.apache.ibatis.annotations.Param;

public interface APIMapper {
    LittleQuestion getLittleQuestionById(@Param("id") Integer id);

    public AnswerAndExplain getAnswerAndExplain(Integer questionId);
}
