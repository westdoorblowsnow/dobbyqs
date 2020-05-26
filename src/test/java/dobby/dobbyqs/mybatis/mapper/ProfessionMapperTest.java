package dobby.dobbyqs.mybatis.mapper;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileNotFoundException;

public class ProfessionMapperTest {
    private static ProfessionMapper mapper;

    @org.junit.BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(ProfessionMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/ProfessionMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(ProfessionMapper.class, builder.openSession(true));
    }

    @org.junit.Test
    public void testSelectAll() throws FileNotFoundException {
        System.out.println(mapper.selectAll());

    }
}
