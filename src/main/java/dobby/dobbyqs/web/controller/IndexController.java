package dobby.dobbyqs.web.controller;

import dobby.dobbyqs.mybatis.service.ProfessionService;
import dobby.dobbyqs.web.bean.GetProfession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    ProfessionService professionService;

    @GetMapping(path = {"/","/index"})
    public String index(Model model){
        final List<GetProfession> getProfessions = professionService.selectGetAll();
        model.addAttribute("professions",getProfessions);
        return "index";
    }

    @GetMapping(path = {"/manage"})
    public String manage (Model model){

        return "Manage";
    }

    @GetMapping("/laTex")
    public String leTex(){
        return "laTex";
    }


}
