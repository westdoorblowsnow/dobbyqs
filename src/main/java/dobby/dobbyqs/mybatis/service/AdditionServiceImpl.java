package dobby.dobbyqs.mybatis.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import dobby.dobbyqs.mybatis.mapper.AdditionMapper;
import dobby.dobbyqs.mybatis.pojo.Addition;
import dobby.dobbyqs.mybatis.service.AdditionService;

@Service
public class AdditionServiceImpl implements AdditionService {

    @Resource
    private AdditionMapper additionMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return additionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Addition record) {
        return additionMapper.insert(record);
    }

    @Override
    public int insertSelective(Addition record) {
        return additionMapper.insertSelective(record);
    }

    @Override
    public Addition selectByPrimaryKey(Integer id) {
        return additionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Addition record) {
        return additionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Addition record) {
        return additionMapper.updateByPrimaryKey(record);
    }

}
