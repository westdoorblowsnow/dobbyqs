package dobby.dobbyqs.mybatis.mapper;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;

public class SubjectMapperTest {
    private static SubjectMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(SubjectMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/SubjectMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(SubjectMapper.class, builder.openSession(true));
    }

    @Test
    public void testSelectByProfessionId() throws FileNotFoundException {
        System.out.println(mapper.selectByProfessionId(1));

    }
}
