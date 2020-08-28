package dobby.dobbyqs.backstage.service;

import dobby.dobbyqs.backstage.bean.BackQuestion;
import dobby.dobbyqs.mybatis.mapper.*;
import dobby.dobbyqs.mybatis.pojo.*;
import dobby.dobbyqs.web.DobbyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BackQuestionServiceImp implements BackQuestionService {

    @Resource
    QuestionMapper questionMapper;

    @Resource
    AdditionMapper additionMapper;

    @Resource
    OptionsMapper optionsMapper;

    @Resource
    SubjectMapper subjectMapper;

    @Resource
    ProfessionMapper professionMapper;

    @Resource
    PaperQuestionLinkMapper linkMapper;

    @Resource
    PaperMapper paperMapper;


    @Override
    public List<BackQuestion> getQuestionByPage(Integer index, Integer size) {
        final List<Question> questions = questionMapper.selectAll((index - 1) * size, size);
        List<BackQuestion> backQuestions = new ArrayList<>(size);
        for (Question question : questions) {
            BackQuestion backQuestion = backQuestionBuild(question);
            backQuestions.add(backQuestion);
        }
        return backQuestions;
    }

    @Override
    public Integer getMaxPage(Integer size) {
        final Integer count = questionMapper.getCount();
        int n = count / size;
        int y = count % size;
        if (count == 0) {
            return 0;
        }
        if (y != 0) {
            return n + 1;
        }
        return n;
    }

    @Transactional
    @Override
    public int updateByQuestionSelective(Question question, Addition addition, Options options) {
//        System.out.println(question);
        final int i = questionMapper.updateByPrimaryKey(question);
//        System.out.println(question + ">>" + i);
        int i1 = 0;
        if (addition.getId() != null) {
            i1 = additionMapper.updateByPrimaryKey(addition);
//            System.out.println(addition);
//            System.out.println(addition + ">>" + i1);
        } else {
            i1 = 1;
        }
        final int i2 = optionsMapper.updateByPrimaryKey(options);
//        System.out.println(options + ">>" + i2);
        if (i == 1 && i1 == 1 && i2 == 1) {
            return 1;
        }
        return 0;
    }

    @Override
    public BackQuestion getQuestionById(Integer id) {
        final Question question = questionMapper.selectByPrimaryKey(id);
        return backQuestionBuild(question);
    }

    @Override
    public BackQuestion getQuestionByNearestId(Integer id, Integer move) {
        Question question = null;
        if (move == null || move == 0) {
            return getQuestionById(id);
        } else if (move < 0) {
            question = questionMapper.selectByTopId(id);
            if (question == null) {
                question = questionMapper.selectByButtonId(id);
            }
        } else {
            question = questionMapper.selectByButtonId(id);
            if (question == null) {
                question = questionMapper.selectByTopId(id);
            }
        }
        return backQuestionBuild(question);
    }


    private BackQuestion backQuestionBuild(Question question) {
        if (Objects.nonNull(question)) {
            String addition = null;
            String[] options = null;
            String[] tags = null;
            String professionSubject = null;
            if (Objects.nonNull(question.getAdditionId())) {
                final Addition addition1 = additionMapper.selectByPrimaryKey(question.getAdditionId());
                addition = addition1.getAddition();
            }
            final Options options1 = optionsMapper.selectByPrimaryKey(question.getOptionsId());
            options = options1.getOptions().split(DobbyUtils.SEPARATOR);
            if (Objects.nonNull(question.getTag())) {
                tags = question.getTag().split(",");
            }
            final Subject subject = subjectMapper.selectByPrimaryKey(question.getSubjectId());
            final Profession profession = professionMapper.selectByPrimaryKey(subject.getProfessionId());

            String[] paperNames = null;
            final List<Integer> ids = linkMapper.selectPaperIdsByQuestionId(question.getId());
            if (ids != null && !ids.isEmpty()) {
                paperNames = new String[ids.size()];
                for (int i = 0; i < ids.size(); i++) {
                    paperNames[i] = paperMapper.selectByPrimaryKey(ids.get(i)).getName();
                }
            }

            professionSubject = profession.getName() + "(" + profession.getCode() + ") " + subject.getName();
            final BackQuestion backQuestion = BackQuestion.create()
                    .question(question)
                    .addition(addition)
                    .options(options)
                    .tags(tags)
                    .professionSubject(professionSubject)
                    .paperNames(paperNames)
                    .answer();
            return backQuestion;
        }
        return null;
    }
}
