package dobby.dobbyqs.mybatis.mapper;

import dobby.dobbyqs.mybatis.pojo.PaperQuestionLink;
import org.apache.ibatis.annotations.Param;

public interface PaperQuestionLinkMapper {
    /**
     * delete by primary key
     * @param paperId primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(@Param("paperId") Integer paperId, @Param("questionId") Integer questionId);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(PaperQuestionLink record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(PaperQuestionLink record);

    /**
     * select by primary key
     * @param paperId primary key
     * @return object by primary key
     */
    PaperQuestionLink selectByPrimaryKey(@Param("paperId") Integer paperId, @Param("questionId") Integer questionId);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(PaperQuestionLink record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(PaperQuestionLink record);
}