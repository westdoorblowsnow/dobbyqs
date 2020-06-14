package dobby.dobbyqs.mybatis.mapper;

import dobby.dobbyqs.mybatis.pojo.Paper;

public interface PaperMapper {
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
    int insert(Paper record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Paper record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Paper selectByPrimaryKey(Integer id);

    /**
     * 查询最新数据
     * @return
     */
    Paper selectLatest();

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Paper record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Paper record);
}