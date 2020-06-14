package dobby.dobbyqs.mybatis.mapper;

import dobby.dobbyqs.mybatis.pojo.Options;

public interface OptionsMapper {
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
    int insert(Options record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Options record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Options selectByPrimaryKey(Integer id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Options record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Options record);


}