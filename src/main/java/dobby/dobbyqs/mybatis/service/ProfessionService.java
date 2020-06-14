package dobby.dobbyqs.mybatis.service;

import dobby.dobbyqs.mybatis.pojo.Profession;
import dobby.dobbyqs.web.bean.GetProfession;

import java.util.List;

public interface ProfessionService {

    int deleteByPrimaryKey(Integer id);

    int insert(Profession record);

    int insertSelective(Profession record);

    Profession selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Profession record);

    int updateByPrimaryKey(Profession record);

    List<GetProfession> selectGetAll();

}
