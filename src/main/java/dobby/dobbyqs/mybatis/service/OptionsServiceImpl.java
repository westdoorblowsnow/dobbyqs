package dobby.dobbyqs.mybatis.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import dobby.dobbyqs.mybatis.mapper.OptionsMapper;
import dobby.dobbyqs.mybatis.pojo.Options;
import dobby.dobbyqs.mybatis.service.OptionsService;

@Service
public class OptionsServiceImpl implements OptionsService {

    @Resource
    private OptionsMapper optionsMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return optionsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Options record) {
        return optionsMapper.insert(record);
    }

    @Override
    public int insertSelective(Options record) {
        int k = 0;
        try {
            k = optionsMapper.insertSelective(record);
        } catch (Exception e) {
            System.out.println(record);
            throw e;
        }
        return k;
    }

    @Override
    public Options selectByPrimaryKey(Integer id) {
        return optionsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Options record) {
        return optionsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Options record) {
        return optionsMapper.updateByPrimaryKey(record);
    }

}
