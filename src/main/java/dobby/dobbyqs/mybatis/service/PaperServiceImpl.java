package dobby.dobbyqs.mybatis.service;

import dobby.dobbyqs.mybatis.mapper.CommonMapper;
import dobby.dobbyqs.mybatis.pojo.*;
import dobby.dobbyqs.web.StringUtils;
import dobby.dobbyqs.web.bean.GetPaper;
import dobby.dobbyqs.web.bean.GetQuestion;
import dobby.dobbyqs.web.bean.PostPaper;
import dobby.dobbyqs.web.WebBeanToPOJO;
import dobby.dobbyqs.web.bean.PostQuestion;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import dobby.dobbyqs.mybatis.mapper.PaperMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaperServiceImpl implements PaperService {

    @Resource
    private CommonMapper commonMapper;

    @Resource
    private PaperMapper paperMapper;

    @Resource
    QuestionService questionService;

    @Resource
    PaperQuestionLinkService paperQuestionLinkService;

    @Resource
    AdditionService additionService;

    @Resource
    OptionsService optionsService;

    @Resource
    SubjectService subjectService;

    @Resource
    ProfessionService professionService;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return paperMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Paper record) {
        return paperMapper.insert(record);
    }

    @Override
    public int insertSelective(Paper record) {
        return paperMapper.insertSelective(record);
    }

    @Override
    public Paper selectByPrimaryKey(Integer id) {
        return paperMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Paper record) {
        return paperMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Paper record) {
        return paperMapper.updateByPrimaryKey(record);
    }

    /**
     * 录入整套试卷
     *
     * @param postPaper
     */
    @Transactional
    @Override
    public void insertPostPaper(PostPaper postPaper) {
        final Paper paper = WebBeanToPOJO.toPaper(postPaper);
        final int insertPaper = paperMapper.insertSelective(paper);
        if (insertPaper != 1) throw new RuntimeException("paper table insert exception");
        final List<PostQuestion> postQuestions = postPaper.getQuestions();
        final HashMap<String, Integer> additionGroupMap = new HashMap<>();
        final HashMap<String, Integer> optionsGroupMap = new HashMap<>();
        for (PostQuestion postQuestion :
                postQuestions) {
            Question question = null;
            Integer additionId = null;
            Integer optionsId = null;

            if (StringUtils.isBlank(postQuestion.getGroup())) {
                if (!StringUtils.isBlank(postQuestion.getAddition())) {
                    Addition addition = WebBeanToPOJO.toAddition(postQuestion.getAddition());
                    final int insertAddition = additionService.insertSelective(addition);
                    if (insertAddition != 1) throw new RuntimeException("addition table insert exception");
                    additionId = addition.getId();
                }
                Options options = WebBeanToPOJO.toOptions(postQuestion.getOptions());
                final int insertOptions = optionsService.insertSelective(options);
                if (insertOptions != 1) throw new RuntimeException("options table insert exception");
                optionsId = options.getId();
            } else {
                if (additionGroupMap.containsKey(postQuestion.getGroup())) {
                    additionId = additionGroupMap.get(postQuestion.getGroup());
                } else {
                    if (!StringUtils.isBlank(postQuestion.getAddition())) {
                        Addition addition = WebBeanToPOJO.toAddition(postQuestion.getAddition());
                        final int insertAddition = additionService.insertSelective(addition);
                        if (insertAddition != 1) throw new RuntimeException("addition table insert exception");
                        additionId = addition.getId();
                        additionGroupMap.put(postQuestion.getGroup(), additionId);
                    }
                }

                if ("B1".equals(postQuestion.getType())) {
                    if (optionsGroupMap.containsKey(postQuestion.getGroup())) {
                        optionsId = optionsGroupMap.get(postQuestion.getGroup());
                    } else {
                        Options options = WebBeanToPOJO.toOptions(postQuestion.getOptions());
                        final int insertOptions = optionsService.insertSelective(options);
                        if (insertOptions != 1) throw new RuntimeException("options table insert exception");
                        optionsId = options.getId();
                        optionsGroupMap.put(postQuestion.getGroup(), optionsId);
                    }
                } else {
                    Options options = WebBeanToPOJO.toOptions(postQuestion.getOptions());
                    final int insertOptions = optionsService.insertSelective(options);
                    if (insertOptions != 1) throw new RuntimeException("options table insert exception");
                    optionsId = options.getId();
                }
            }
            question = WebBeanToPOJO.toQuestion(postQuestion, additionId, optionsId);
            question.setSubjectId(postPaper.getSubjectId());
            final int insertQuestion = questionService.insertSelective(question);
            if (insertQuestion != 1) throw new RuntimeException("question table insert exception");
            final PaperQuestionLink paperQuestionLink = WebBeanToPOJO.toPaperQuestionLink(paper.getId(), question.getId(), postQuestion.getScoe());
            final int insertPaperQuestion = paperQuestionLinkService.insertSelective(paperQuestionLink);
            if (insertPaperQuestion != 1) throw new RuntimeException("paper_question_link table insert exception");
        }
    }

    /**
     * 查询整张试卷所有试题
     *
     * @param id 试卷id，id为null或小于等于0，则查询最新一张试卷
     * @return
     */
    @Override
    public GetPaper getPaperQuestionsById(Integer id) {
        GetPaper getPaper = new GetPaper();
        Paper paper = null;
        if (id == null || id < 0) paper = paperMapper.selectLatest();
        else paper = paperMapper.selectByPrimaryKey(id);
        getPaper.setId(paper.getId());
        getPaper.setName(paper.getName());
        getPaper.setTag(paper.getTag());
        final Subject subject = subjectService.selectByPrimaryKey(paper.getSubjectId());
        getPaper.setSubject(subject.getName());
        getPaper.setProfession(professionService.selectByPrimaryKey(subject.getProfessionId()).getName());
        getPaper.setSubjectId(paper.getSubjectId());
        List<GetQuestion> getQuestions = new ArrayList<>();
        getPaper.setQuestions(getQuestions);
        final List<Integer> questionIds = paperQuestionLinkService.getQuestionsIdsByPaperId(paper.getId());
        for (Integer questionId :
                questionIds) {
            final GetQuestion getQuestion = questionService.selectFullGetById(questionId);
            getQuestions.add(getQuestion);
        }
        return getPaper;
    }

    @Override
    public List<Map<String, Object>> getAllPapers() {
        return commonMapper.getAllPapers();
    }


}
