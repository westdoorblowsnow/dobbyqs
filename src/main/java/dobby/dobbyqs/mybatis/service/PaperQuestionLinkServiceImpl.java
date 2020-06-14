package dobby.dobbyqs.mybatis.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import dobby.dobbyqs.mybatis.pojo.PaperQuestionLink;
import dobby.dobbyqs.mybatis.mapper.PaperQuestionLinkMapper;
import dobby.dobbyqs.mybatis.service.PaperQuestionLinkService;

import java.util.List;

@Service
public class PaperQuestionLinkServiceImpl implements PaperQuestionLinkService {

    @Resource
    private PaperQuestionLinkMapper paperQuestionLinkMapper;

    @Override
    public int deleteByPrimaryKey(Integer paperId, Integer questionId) {
        return paperQuestionLinkMapper.deleteByPrimaryKey(paperId, questionId);
    }

    @Override
    public int insert(PaperQuestionLink record) {
        return paperQuestionLinkMapper.insert(record);
    }

    @Override
    public int insertSelective(PaperQuestionLink record) {
        return paperQuestionLinkMapper.insertSelective(record);
    }

    @Override
    public PaperQuestionLink selectByPrimaryKey(Integer paperId, Integer questionId) {
        return paperQuestionLinkMapper.selectByPrimaryKey(paperId, questionId);
    }

    @Override
    public int updateByPrimaryKeySelective(PaperQuestionLink record) {
        return paperQuestionLinkMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PaperQuestionLink record) {
        return paperQuestionLinkMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Integer> getQuestionsIdsByPaperId(Integer paperId) {
        return paperQuestionLinkMapper.selectQuestionIdsByPaperId(paperId);
    }

}
