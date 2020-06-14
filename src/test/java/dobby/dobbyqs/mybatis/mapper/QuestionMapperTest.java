package dobby.dobbyqs.mybatis.mapper;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;

public class QuestionMapperTest {
    private static QuestionMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(QuestionMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/QuestionMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(QuestionMapper.class, builder.openSession(true));
    }

    @Test
    public void testSelectAll() throws FileNotFoundException {
        System.out.println(mapper.selectAll(0,100000));

    }
}
