package dobby.dobbyqs.mybatis.service;

import dobby.dobbyqs.mybatis.pojo.Diagram;
import dobby.dobbyqs.mybatis.pojo.DiagramKey;

public interface DiagramService {
    int deleteByPrimaryKey(DiagramKey key);

    int insert(Diagram record);

    Diagram selectByPrimaryKey(DiagramKey key);
}
