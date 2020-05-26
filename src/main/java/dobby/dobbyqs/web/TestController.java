package dobby.dobbyqs.web;

import dobby.dobbyqs.mybatis.pojo.Profession;
import dobby.dobbyqs.mybatis.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    ProfessionService professionService;

    @GetMapping(path = "/test")
    public Profession test(){
        return  professionService.selectByPrimaryKey(1);
    }
}
