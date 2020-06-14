package dobby.dobbyqs.mybatis.mapper;

import dobby.dobbyqs.mybatis.pojo.Subject;

import java.util.List;

public interface SubjectMapper {
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
    int insert(Subject record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Subject record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Subject selectByPrimaryKey(Integer id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Subject record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Subject record);

    List<Subject> selectByProfessionId(Integer profesionId);

}