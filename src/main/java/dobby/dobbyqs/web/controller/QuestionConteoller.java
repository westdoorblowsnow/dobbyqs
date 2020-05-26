package dobby.dobbyqs.web.controller;

import dobby.dobbyqs.mybatis.service.ProfessionService;
import dobby.dobbyqs.web.bean.GetProfesstion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
class QuestionController {
    @Autowired
    ProfessionService professionService;

    @ResponseBody
    @GetMapping(path = "/profession/get/all")
    public List<GetProfesstion> getProfesstion(){
        return  professionService.selectGetAll();
    }

}
