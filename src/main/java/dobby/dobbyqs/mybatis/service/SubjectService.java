package dobby.dobbyqs.mybatis.service;

import dobby.dobbyqs.mybatis.pojo.Subject;

public interface SubjectService {


    int deleteByPrimaryKey(Integer id);

    int insert(Subject record);

    int insertSelective(Subject record);

    Subject selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKey(Subject record);

}
