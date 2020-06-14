package dobby.dobbyqs.mybatis.service;

import dobby.dobbyqs.mybatis.mapper.AdditionMapper;
import dobby.dobbyqs.mybatis.mapper.OptionsMapper;
import dobby.dobbyqs.mybatis.mapper.SubjectMapper;
import dobby.dobbyqs.mybatis.pojo.Addition;
import dobby.dobbyqs.mybatis.pojo.Options;
import dobby.dobbyqs.mybatis.pojo.Subject;
import dobby.dobbyqs.web.POJOToWebBean;
import dobby.dobbyqs.web.bean.GetQuestion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import dobby.dobbyqs.mybatis.pojo.Question;
import dobby.dobbyqs.mybatis.mapper.QuestionMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    Logger logger = LoggerFactory.getLogger("QuestionService");

    @Resource
    private QuestionMapper questionMapper;
    @Resource
    private AdditionMapper additionMapper;
    @Resource
    private OptionsMapper optionsMapper;
    @Resource
    private SubjectMapper subjectMapper;

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
    public List<GetQuestion> selectAllGetByPage(Integer currIndex, Integer pageSize) {
        List<Question> questions = questionMapper.selectAll((currIndex - 1) * pageSize, pageSize);
        List<GetQuestion> getQuestions = new ArrayList<>();
        for (Question question : questions) {
            Addition addition = null;
            if (question.getAdditionId() != null)
                addition = additionMapper.selectByPrimaryKey(question.getAdditionId());

            Options options = null;
            if (question.getOptionsId() != null)
                options = optionsMapper.selectByPrimaryKey(question.getOptionsId());

            Subject subject = null;
            if (question.getSubjectId() != null) ;
            subject = subjectMapper.selectByPrimaryKey(question.getSubjectId());
            GetQuestion getQuestion = POJOToWebBean.toGetQuestion(question, options, addition);
            getQuestions.add(getQuestion);
        }
        return getQuestions;
    }

    @Override
    public GetQuestion selectFullGetById(int id) {
        final Question question = questionMapper.selectByPrimaryKey(id);
        if (question != null) {
            Addition addition = null;
            if (question.getAdditionId() != null)
                addition = additionMapper.selectByPrimaryKey(question.getAdditionId());

            Options options = null;
            if (question.getOptionsId() != null)
                options = optionsMapper.selectByPrimaryKey(question.getOptionsId());
            GetQuestion getQuestion = POJOToWebBean.toGetQuestion(question, options, addition);
            return getQuestion;
        }
        return null;
    }
}
