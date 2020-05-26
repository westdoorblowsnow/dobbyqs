package dobby.dobbyqs.mybatis.service;

import dobby.dobbyqs.mybatis.pojo.Paper;
public interface PaperService{


    int deleteByPrimaryKey(Integer id);

    int insert(Paper record);

    int insertSelective(Paper record);

    Paper selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Paper record);

    int updateByPrimaryKey(Paper record);

}
