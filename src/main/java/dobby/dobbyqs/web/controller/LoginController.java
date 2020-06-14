package dobby.dobbyqs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @PostMapping("trylogin")
    public String tryLogin(String username,String password){
        return "index";
    }

}
