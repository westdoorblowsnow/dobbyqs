package dobby.dobbyqs.web.controller;

import dobby.dobbyqs.mybatis.service.QuestionService;
import dobby.dobbyqs.web.bean.GetQuestion;
import dobby.dobbyqs.web.HttpMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @ResponseBody
    @GetMapping(path = "/get/question/all")
    public HttpMessage getQuestions() {
        final List<GetQuestion> getQuestions = questionService.selectAllGetByPage(1,100000);
        if (getQuestions!=null) return HttpMessage.get(getQuestions);
        else return HttpMessage.notFound();
    }

    @ResponseBody
    @GetMapping(path = "/get/question/id", produces = "application/json")
    public HttpMessage getQuestionById(@RequestParam("id") Integer id) {
        if (id == null || id < 0) return HttpMessage.invalidArgument("id=" + id);
        final GetQuestion getQuestion = questionService.selectFullGetById(id);
        if (getQuestion == null) return HttpMessage.notFound();
        else return HttpMessage.get(getQuestion);
    }
}
