package dobby.dobbyqs.mybatis.service;

import dobby.dobbyqs.web.bean.GetProfesstion;
import dobby.dobbyqs.web.bean.GetQuestion;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import dobby.dobbyqs.mybatis.pojo.Question;
import dobby.dobbyqs.mybatis.mapper.QuestionMapper;
import dobby.dobbyqs.mybatis.service.QuestionService;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Resource
    private QuestionMapper questionMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return questionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Question record) {
        return questionMapper.insert(record);
    }

    @Override
    public int insertSelective(Question record) {
        return questionMapper.insertSelective(record);
    }

    @Override
    public Question selectByPrimaryKey(Integer id) {
        return questionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Question record) {
        return questionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Question record) {
        return questionMapper.updateByPrimaryKey(record);
    }

    @Override
    public GetQuestion selectAllGet() {
        return null;
    }


}
