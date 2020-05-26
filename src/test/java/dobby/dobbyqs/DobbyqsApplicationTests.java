package dobby.dobbyqs;

import dobby.dobbyqs.mybatis.mapper.ProfessionMapper;
import dobby.dobbyqs.mybatis.service.ProfessionService;
import dobby.dobbyqs.web.DobbyUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class DobbyqsApplicationTests {

    @Test
    void contextLoads(@Autowired ProfessionService professionService) {
        System.out.println(professionService.selectByPrimaryKey(2));

    }

    @Test
    void testSelectAll(@Autowired ProfessionMapper mapper){
        System.out.println(mapper.selectAll());
    }

    @Test
    void testGetProfession(@Autowired ProfessionService service){
        System.out.println(service.selectGetAll());
    }

    @Test
    void testUtil(){
        System.out.println(Integer.toBinaryString(DobbyUtils.intAnswer("ABCDFOGG")));
        System.out.println(DobbyUtils.stringAnswer(DobbyUtils.intAnswer("ABCDFOGF")));
        System.out.println(DobbyUtils.listOptions("我爱你##一万年##爱你##禁得起##考验"));
        System.out.println(DobbyUtils.stringOptions(DobbyUtils.listOptions("我爱你##一万年##爱你##禁得起##考验")));
    }

}
