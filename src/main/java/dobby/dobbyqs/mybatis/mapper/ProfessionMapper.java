package dobby.dobbyqs.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import dobby.dobbyqs.mybatis.pojo.Profession;

import java.util.List;

public interface ProfessionMapper {

    List<Profession> selectAll();

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
    int insert(Profession record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Profession record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Profession selectByPrimaryKey(Integer id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Profession record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Profession record);
}