package dobby.dobbyqs.mybatis.mapper;

import java.util.List;
import java.util.Map;

public interface CommonMapper {
    /**
     * 获取所有试卷信息列表
     * @return
     */
    List<Map<String,Object>> getAllPapers();
}
