package dobby.dobbyqs.mybatis.mapper;

import dobby.dobbyqs.mybatis.pojo.Question;
import dobby.dobbyqs.web.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

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
        final List<Question> questions = mapper.selectAll(1, 100000);
        for (Question question: questions) {
            if (StringUtils.isBlank(question.getExplain())){
                question.setExplain("略。");
                final int i = mapper.updateByPrimaryKey(question);
                if (i!=1){
                    System.out.println(question.toString()+"：修改失败！");
                }else {
                    System.out.println(question.toString()+"：修改成功！");
                }
            }
        }
    }
}
