package dobby.dobbyqs.mybatis.service;

import dobby.dobbyqs.mybatis.mapper.DiagramMapper;
import dobby.dobbyqs.mybatis.pojo.Diagram;
import dobby.dobbyqs.mybatis.pojo.DiagramKey;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DiagramServiceImp implements DiagramService {

    @Resource
    DiagramMapper diagramMapper;

    @Override
    public int deleteByPrimaryKey(DiagramKey key) {
        final int key1 = diagramMapper.deleteByPrimaryKey(key);
        return key1;
    }

    @Override
    public int insert(Diagram record) {
        final int insert = diagramMapper.insert(record);


        return insert;
    }

    @Override
    public Diagram selectByPrimaryKey(DiagramKey key) {
        final Diagram diagram = diagramMapper.selectByPrimaryKey(key);

        return diagram;
    }


}
