package dobby.dobbyqs.web.controller;

import dobby.dobbyqs.mybatis.service.QuestionService;
import dobby.dobbyqs.web.bean.GetQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class QuestionsController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/questions")
    public String questions(Model model) {
        final List<GetQuestion> getQuestions = questionService.selectAllGetByPage(1,100);
        model.addAttribute("questions",getQuestions);
        return "questions";
    }
}
