package dobby.dobbyqs.mybatis.service;

import dobby.dobbyqs.mybatis.mapper.SubjectMapper;
import dobby.dobbyqs.mybatis.pojo.Subject;
import dobby.dobbyqs.web.POJOToWebBean;
import dobby.dobbyqs.web.bean.GetProfesstion;
import dobby.dobbyqs.web.bean.GetSubject;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import dobby.dobbyqs.mybatis.mapper.ProfessionMapper;
import dobby.dobbyqs.mybatis.pojo.Profession;
import dobby.dobbyqs.mybatis.service.ProfessionService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessionServiceImpl implements ProfessionService{

    @Resource
    private ProfessionMapper professionMapper;
    @Resource
    private SubjectMapper subjectMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return professionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Profession record) {
        return professionMapper.insert(record);
    }

    @Override
    public int insertSelective(Profession record) {
        return professionMapper.insertSelective(record);
    }

    @Override
    public Profession selectByPrimaryKey(Integer id) {
        return professionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Profession record) {
        return professionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Profession record) {
        return professionMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<GetProfesstion> selectGetAll() {
        List<Profession> professions = professionMapper.selectAll();
        if (!professions.isEmpty()){
            List<GetProfesstion> getProfesstions = new ArrayList<>();
            for (Profession profession:
                 professions) {
                List<Subject> subjects = subjectMapper.selectByProfessionId(profession.getId());
                GetProfesstion getProfesstion = POJOToWebBean.toGetProfession(profession,subjects);
                getProfesstions.add(getProfesstion);
            }
            return getProfesstions;
        }
        return null;
    }

}
