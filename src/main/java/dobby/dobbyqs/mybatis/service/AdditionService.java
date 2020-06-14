package dobby.dobbyqs.mybatis.service;

import dobby.dobbyqs.mybatis.pojo.Addition;

public interface AdditionService {

    int deleteByPrimaryKey(Integer id);

    int insert(Addition record);

    int insertSelective(Addition record);

    Addition selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Addition record);

    int updateByPrimaryKey(Addition record);

}
