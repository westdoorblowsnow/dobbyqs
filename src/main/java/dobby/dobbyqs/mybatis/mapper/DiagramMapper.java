package dobby.dobbyqs.mybatis.mapper;

import dobby.dobbyqs.mybatis.pojo.Diagram;
import dobby.dobbyqs.mybatis.pojo.DiagramKey;

public interface DiagramMapper {
    int deleteByPrimaryKey(DiagramKey key);

    int insert(Diagram record);

    int insertSelective(Diagram record);

    Diagram selectByPrimaryKey(DiagramKey key);

    int updateByPrimaryKeySelective(Diagram record);

    int updateByPrimaryKey(Diagram record);
}