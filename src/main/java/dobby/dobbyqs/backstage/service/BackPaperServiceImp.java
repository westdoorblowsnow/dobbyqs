package dobby.dobbyqs.backstage.service;

import dobby.dobbyqs.backstage.bean.BackQuestion;
import dobby.dobbyqs.mybatis.mapper.PaperMapper;
import dobby.dobbyqs.mybatis.mapper.PaperQuestionLinkMapper;
import dobby.dobbyqs.mybatis.mapper.ProfessionMapper;
import dobby.dobbyqs.mybatis.mapper.SubjectMapper;
import dobby.dobbyqs.mybatis.pojo.Paper;
import dobby.dobbyqs.mybatis.pojo.Profession;
import dobby.dobbyqs.mybatis.pojo.Subject;
import dobby.dobbyqs.mybatis.service.SubjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class BackPaperServiceImp implements BackPaperService {

    @Resource
    PaperMapper paperMapper;

    @Resource
    PaperQuestionLinkMapper linkMapper;

    @Resource
    BackQuestionService questionService;

    @Resource
    SubjectMapper subjectMapper;

    @Resource
    ProfessionMapper professionMapper;


    @Override
    public Paper getPaperByIndex(Integer index) {
        if (index == null || index < 0) {
            index = 0;
        }
        final Paper paper = paperMapper.getPaperByIndex(index);
        return paper;
    }


    @Override
    public List<Paper> getPapersByProfessionId(Integer professionId) {
        List<Subject> subjects = subjectMapper.selectByProfessionId(professionId);
        List<Paper> papers = new ArrayList<>();
        for (Subject s : subjects) {
            List<Paper> list = paperMapper.selectPapersBySubjectId(s.getId());
            papers.addAll(list);
        }
        return papers;
    }

    @Override
    public Profession getProfessionByProfessionId(Integer professionId) {
        Profession profession = professionMapper.selectByPrimaryKey(professionId);
        return profession;
    }


    @Override
    public Map<String, List<BackQuestion>> getQuestionsByPaperId(Integer paperId) {
        final List<Integer> questionIds = linkMapper.selectQuestionIdsByPaperId(paperId);
        Map<String, List<BackQuestion>> map = new HashMap<>();
        for (Integer id : questionIds) {
            final BackQuestion backQuestion = questionService.getQuestionById(id);
            final String type = backQuestion.getQuestion().getType();
            if (map.containsKey(type)) {
                map.get(type).add(backQuestion);
            } else {
                map.put(type, new ArrayList<>());
                map.get(type).add(backQuestion);
            }
        }
        return map;
    }

    @Override
    public Paper getPaperById(Integer id) {
        if (Objects.isNull(id)) return null;
        Paper paper = paperMapper.selectByPrimaryKey(id);
        return paper;
    }

    @Override
    public List<Paper> getAllPaper() {
        List<Paper> papers = paperMapper.selectAllPapers();
        return papers;
    }
}
