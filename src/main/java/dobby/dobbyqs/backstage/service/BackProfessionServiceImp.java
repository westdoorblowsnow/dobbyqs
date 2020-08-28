package dobby.dobbyqs.backstage.service;

import dobby.dobbyqs.backstage.bean.BackProfession;
import dobby.dobbyqs.mybatis.mapper.ProfessionMapper;
import dobby.dobbyqs.mybatis.mapper.SubjectMapper;
import dobby.dobbyqs.mybatis.pojo.Profession;
import dobby.dobbyqs.mybatis.pojo.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class BackProfessionServiceImp implements BackProfessionService {

    @Resource
    ProfessionMapper professionMapper;

    @Resource
    SubjectMapper subjectMapper;

    @Override
    public List<BackProfession> getAllBackProfessions() {
        List<BackProfession> backProfessions = new ArrayList<>();
        final List<Profession> professions = professionMapper.selectAll();
        for (Profession profession : professions) {
            final List<Subject> subjects = subjectMapper.selectByProfessionId(profession.getId());
            BackProfession backProfession = new BackProfession();
            backProfession.setProfession(profession);
            backProfession.setSubjects(subjects);
            backProfessions.add(backProfession);
        }
        return backProfessions;
    }

    @Transactional
    @Override
    public int updateBackProfession(BackProfession backProfession) {
        final Profession profession = backProfession.getProfession();
        final List<Subject> subjects = backProfession.getSubjects();
        final int i = professionMapper.updateByPrimaryKey(profession);
        if (i != 1) return 0;
        for (Subject subject : subjects) {
            final int i1 = subjectMapper.updateByPrimaryKey(subject);
            if (i1 != 1) return 0;
        }
        return 1;
    }

    @Transactional
    @Override
    public int insertBackProfession(BackProfession backProfession) {
        final Profession profession = backProfession.getProfession();
        final List<Subject> subjects = backProfession.getSubjects();
        final int insert = professionMapper.insert(profession);
        if (insert == 1) {
            for (Subject subject : subjects) {
                subject.setProfessionId(profession.getId());
                final int insert1 = subjectMapper.insert(subject);
                if (insert1!=1){
                    throw new RuntimeException("科目插入错误！");
                }
            }
        }else {
            throw new RuntimeException("专业插入错误！");
        }
        return 1;
    }
}
