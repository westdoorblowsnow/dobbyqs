package dobby.dobbyqs.mybatis.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import dobby.dobbyqs.mybatis.pojo.Subject;
import dobby.dobbyqs.mybatis.mapper.SubjectMapper;
import dobby.dobbyqs.mybatis.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Resource
    private SubjectMapper subjectMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return subjectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Subject record) {
        return subjectMapper.insert(record);
    }

    @Override
    public int insertSelective(Subject record) {
        return subjectMapper.insertSelective(record);
    }

    @Override
    public Subject selectByPrimaryKey(Integer id) {
        return subjectMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Subject record) {
        return subjectMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Subject record) {
        return subjectMapper.updateByPrimaryKey(record);
    }

}
