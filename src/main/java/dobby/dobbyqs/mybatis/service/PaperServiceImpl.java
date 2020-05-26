package dobby.dobbyqs.mybatis.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import dobby.dobbyqs.mybatis.pojo.Paper;
import dobby.dobbyqs.mybatis.mapper.PaperMapper;
import dobby.dobbyqs.mybatis.service.PaperService;
@Service
public class PaperServiceImpl implements PaperService{

    @Resource
    private PaperMapper paperMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return paperMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Paper record) {
        return paperMapper.insert(record);
    }

    @Override
    public int insertSelective(Paper record) {
        return paperMapper.insertSelective(record);
    }

    @Override
    public Paper selectByPrimaryKey(Integer id) {
        return paperMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Paper record) {
        return paperMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Paper record) {
        return paperMapper.updateByPrimaryKey(record);
    }

}
