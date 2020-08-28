package dobby.dobbyqs.backstage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BackIndexController {


    @GetMapping("/backstage/index.html")
    public String getIndex(){
        return "backstage/index.html";
    }

}
