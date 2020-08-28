package dobby.dobbyqs.mybatis.service;

import dobby.dobbyqs.mybatis.mapper.SubjectMapper;
import dobby.dobbyqs.mybatis.pojo.Subject;
import dobby.dobbyqs.web.POJOToWebBean;
import dobby.dobbyqs.web.bean.GetProfession;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import dobby.dobbyqs.mybatis.mapper.ProfessionMapper;
import dobby.dobbyqs.mybatis.pojo.Profession;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class ProfessionServiceImpl implements ProfessionService {

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
        final int i = professionMapper.insertSelective(record);
        System.out.println(record);
        return i;
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
    public List<GetProfession> selectGetAll() {
        List<Profession> professions = professionMapper.selectAll();
        if (!professions.isEmpty()) {
            List<GetProfession> getProfesstions = new ArrayList<>();
            for (Profession profession :
                    professions) {
                List<Subject> subjects = subjectMapper.selectByProfessionId(profession.getId());
                GetProfession getProfesstion = POJOToWebBean.toGetProfession(profession, subjects);
                getProfesstions.add(getProfesstion);
            }
            getProfesstions.sort(new Comparator<GetProfession>() {
                @Override
                public int compare(GetProfession o1, GetProfession o2) {
                    if (Objects.isNull(o1.getCode())) {
                        return -1;
                    }
                    if (Objects.isNull(o2)) {
                        return 1;
                    }
                    return o1.getCode().compareTo(o2.getCode());
                }
            });
            return getProfesstions;
        }
        return null;
    }

}
