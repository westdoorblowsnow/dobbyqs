package dobby.dobbyqs.backstage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dobby.dobbyqs.backstage.bean.BackQuestion;
import dobby.dobbyqs.backstage.bean.ImgHelper;
import dobby.dobbyqs.backstage.service.BackQuestionService;
import dobby.dobbyqs.mybatis.pojo.Addition;
import dobby.dobbyqs.mybatis.pojo.Options;
import dobby.dobbyqs.mybatis.pojo.Question;
import dobby.dobbyqs.web.DobbyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class BackQuestionController {

    @Resource
    BackQuestionService service;

    Logger logger = LoggerFactory.getLogger(BackQuestionController.class);

    @ResponseBody
    @PostMapping(value = "/backstage/question/update", consumes = "application/json;utf-8")
    public Map<String, Object> update(@RequestBody BackQuestion backQuestion) {
        Map<String, Object> map = new HashMap<>();
        if (backQuestionUpdateValid(backQuestion)) {
            logger.info("backQuestion update: " + backQuestion.getQuestion().getId() + " >> " + backQuestion.toString());

//        System.out.println(cd);
//        System.out.println(backQuestion);
            final String answer = backQuestion.getAnswer();
            backQuestion.getQuestion().setAnswer(DobbyUtils.intAnswer(answer));
            Question question = backQuestion.getQuestion();

            Addition addition = new Addition();
            addition.setId(question.getAdditionId());
            addition.setAddition(backQuestion.getAddition());
//        System.out.println(addition);

            Options options = new Options();
            options.setId(question.getOptionsId());
            String[] ops = backQuestion.getOptions();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < ops.length; i++) {
                builder.append(ops[i]);
                if (i != ops.length - 1) {
                    builder.append(DobbyUtils.SEPARATOR);
                }
            }
            options.setOptions(builder.toString());
//            System.out.println(builder);
            int k = 0;
            try {
                k = service.updateByQuestionSelective(question, addition, options);
                map.put("code", 1);
            } catch (Exception e) {
                map.put("code", 0);
            }
        } else {
            map.put("code", 0);
        }
        final Integer code = (Integer) map.get("code");
        if (code == 1) {
            logger.info("backQuestion update: " + backQuestion.getQuestion().getId() + " >> success");
        } else {
            logger.info("backQuestion update: " + backQuestion.getQuestion().getId() + " >> fail");
        }
        return map;
    }

    @GetMapping("/backstage/Question.html")
    public String question(Integer id, Integer move, Model model) throws JsonProcessingException {
        if (Objects.isNull(id)) {
            id = 10086;
        }
        BackQuestion backQuestion = service.getQuestionByNearestId(id, move);
        BackQuestion backQuestion1 = new BackQuestion();
        backQuestion1.setQuestion(new Question());
        backQuestion1.setOptions(new String[backQuestion.getOptions().length]);
        backQuestion1.getQuestion().setId(backQuestion.getQuestion().getId());
        backQuestion1.getQuestion().setOptionsId(backQuestion.getQuestion().getOptionsId());
        backQuestion1.getQuestion().setAdditionId(backQuestion.getQuestion().getAdditionId());

        ObjectMapper mapper = new ObjectMapper();
        String jsonQuestion = mapper.writeValueAsString(backQuestion1);
        model.addAttribute("jsonQuestion", jsonQuestion);
        model.addAttribute("backQuestion", backQuestion);
        model.addAttribute("id", backQuestion.getQuestion().getId());
        return "backstage/updatableQuestion";
    }

    @ResponseBody
    @GetMapping(value = "/backstage/Question", produces = "application/json;utf-8")
    public BackQuestion questionRest(Integer id) throws JsonProcessingException {
        if (Objects.isNull(id)) {
            id = 10086;
        }
        final BackQuestion backQuestion = service.getQuestionById(id);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonQuestion = mapper.writeValueAsString(backQuestion);
//        System.out.println(jsonQuestion);
        return backQuestion;
    }


    @GetMapping("/backstage/Questions.html")
    public String questions(Integer index, Integer size, Model model) {
//        System.out.println(index + " " + size);
        if (Objects.isNull(index) || Objects.isNull(size)) {
            index = 1;
            size = 2;
        }
        if (index <= 0) {
            index = 1;
        }
        if (size < 1) {
            size = 1;
        } else if (size > 200) {
            size = 200;
        }
        final Integer maxPage = service.getMaxPage(size);
        if (index > maxPage) {
            index = maxPage;
        }
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("pageIndex", index);
        model.addAttribute("pageSize", size);
        if (maxPage != 0) {
            final List<BackQuestion> backQuestions = service.getQuestionByPage(index, size);
            model.addAttribute("backQuestions", backQuestions);
            model.addAttribute("imgHelper", new ImgHelper());
        }
        return "backstage/updatableQuestions";
    }

    @ResponseBody
    @GetMapping("/backstage/questions")
    public List<BackQuestion> questionsRest(Integer index, Integer size) {
//        System.out.println(index + " " + size);
        if (Objects.isNull(index) || Objects.isNull(size)) {
            index = 1;
            size = 20;
        }
        if (index <= 0) {
            index = 1;
        }
        if (size <= 10) {
            size = 10;
        } else if (size > 200) {
            size = 200;
        }
        final Integer maxPage = service.getMaxPage(size);
        if (index > maxPage) {
            index = maxPage;
        }
        final List<BackQuestion> questionByPage = service.getQuestionByPage(index, size);
//        System.out.println(questionByPage.size());
        return questionByPage;
    }

    @ResponseBody
    @GetMapping("/backstage/questions/count")
    public Map<String, Object> questionCount() {
        final Integer maxPage = service.getMaxPage(1);
        Map<String, Object> map = new HashMap<>();
        map.put("countQuestion", maxPage);
        return map;
    }

    private boolean backQuestionUpdateValid(BackQuestion backQuestion) {
        if (Objects.isNull(backQuestion)) return false;
        if (DobbyUtils.isAnyNull(backQuestion.getQuestion(), backQuestion.getOptions(), backQuestion.getAnswer()))
            return false;
        if (DobbyUtils.isAnyNull(backQuestion.getQuestion().getQuestion(), backQuestion.getQuestion().getType(), backQuestion.getQuestion().getOptionsId()))
            return false;
        return true;
    }
}
