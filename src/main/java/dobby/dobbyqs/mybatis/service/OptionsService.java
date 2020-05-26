package dobby.dobbyqs.mybatis.service;

import dobby.dobbyqs.mybatis.pojo.Options;
public interface OptionsService{


    int deleteByPrimaryKey(Integer id);

    int insert(Options record);

    int insertSelective(Options record);

    Options selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Options record);

    int updateByPrimaryKey(Options record);

}
