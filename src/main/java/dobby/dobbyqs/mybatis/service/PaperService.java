package dobby.dobbyqs.mybatis.service;

import dobby.dobbyqs.mybatis.pojo.Paper;
import dobby.dobbyqs.mybatis.pojo.Question;
import dobby.dobbyqs.web.bean.GetPaper;
import dobby.dobbyqs.web.bean.PostPaper;

import java.util.List;
import java.util.Map;

public interface PaperService {

    int deleteByPrimaryKey(Integer id);

    int insert(Paper record);

    int insertSelective(Paper record);

    Paper selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Paper record);

    int updateByPrimaryKey(Paper record);

    /**
     * 插入整张试卷
     * @param postPaper
     */
    void insertPostPaper(PostPaper postPaper);

    GetPaper getPaperQuestionsById(Integer id);

    List<Map<String,Object>> getAllPapers();

}
