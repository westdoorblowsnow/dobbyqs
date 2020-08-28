package dobby.dobbyqs.backstage.controller;

import dobby.dobbyqs.mybatis.pojo.Diagram;
import dobby.dobbyqs.mybatis.pojo.DiagramKey;
import dobby.dobbyqs.mybatis.service.DiagramService;
import dobby.dobbyqs.web.DobbyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

@Controller
public class BackImageController {

    @Resource
    DiagramService service;

    @ResponseBody
    @GetMapping(value = "/backstage/image",produces = "image/jpeg")
    public byte[] image(Integer questionId,Integer num) throws UnsupportedEncodingException {
        if (Objects.isNull(questionId)||Objects.isNull(num)) return null;
        DiagramKey diagramKey = new DiagramKey();
        diagramKey.setQuestionId(questionId);
        diagramKey.setNum(num);
        final Diagram diagram = service.selectByPrimaryKey(diagramKey);
        if (Objects.isNull(diagram)) return null;
        else return DobbyUtils.base64StringToBytes(diagram.getDiagram());
    }
}
