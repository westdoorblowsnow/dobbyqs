package dobby.dobbyqs.mybatis.mapper;

import dobby.dobbyqs.mybatis.pojo.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionMapper {

    List<Question> selectAll( @Param("currIndex")Integer currIndex, @Param("pageSize")Integer pageSize);

    Integer getCount();

    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(Question record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Question record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Question selectByPrimaryKey(Integer id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Question record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Question record);

    Question selectByTopId(@Param("id") Integer id);

    Question selectByButtonId(@Param("id") Integer id);
}