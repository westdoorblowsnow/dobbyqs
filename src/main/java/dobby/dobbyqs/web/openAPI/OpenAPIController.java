package dobby.dobbyqs.web.openAPI;

import dobby.dobbyqs.web.openAPI.pojo.APIMessage;
import dobby.dobbyqs.web.openAPI.pojo.APIPostMessage;
import dobby.dobbyqs.web.openAPI.pojo.AnswerAndExplain;
import dobby.dobbyqs.web.openAPI.pojo.LittleQuestion;
import dobby.dobbyqs.web.openAPI.service.APIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OpenAPIController {
    @Resource
    APIService apiService;

    @GetMapping("/api/question")
    public LittleQuestion getLittleQuestionById(Integer id){
        final LittleQuestion questionById = apiService.getLittleQuestionById(id);
        return questionById;
    }

    @PostMapping("/api")
    public APIMessage openAPI(@RequestBody APIPostMessage apiPostMessage){
        final boolean valid = APIPostMessage.isValid(apiPostMessage);
        if (!valid) return APIMessage.parameterInvalid();
        switch (apiPostMessage.getCode()){
            case APIPostMessage.CODE_LITTLE_QUESTION:
                final LittleQuestion littleQuestion = apiService.getLittleQuestionById(apiPostMessage.getQuestionId());
                if (littleQuestion==null) return APIMessage.notFound("没有此题",null,null);
                else return APIMessage.ok("取得题目",null,littleQuestion);
            case APIPostMessage.CODE_ANSWER_AND_EXPLAIN:
                final AnswerAndExplain answerAndExplain = apiService.getAnswerAndExplain(apiPostMessage.getQuestionId());
                if (answerAndExplain==null) return APIMessage.notFound("没有此题",null,null);
                else return APIMessage.ok("取得答案/解析",null,answerAndExplain);
            default:
                return APIMessage.parameterInvalid("不支持的资源码");
        }
    }
}
