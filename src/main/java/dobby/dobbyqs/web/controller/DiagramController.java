package dobby.dobbyqs.web.controller;

import dobby.dobbyqs.mybatis.pojo.Diagram;
import dobby.dobbyqs.mybatis.pojo.DiagramKey;
import dobby.dobbyqs.mybatis.service.DiagramService;
import dobby.dobbyqs.web.HttpMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class DiagramController {

    @Resource
    DiagramService diagramService;

    @GetMapping("/diagram")
    public String diagram(){
        return "diagram";
    }


    @PostMapping(path = "/diagram/up")
    public HttpMessage diagramUp(Integer questionId,Integer num ,MultipartFile image) throws IOException {
        System.out.println(questionId);
        System.out.println(num);
        System.out.println(image.getName());
        Diagram diagram = new Diagram();
        diagram.setQuestionId(questionId);
        diagram.setNum(num);
        diagram.setType(image.getContentType());
        System.out.println(image.getContentType());
        diagram.setImage(image.getBytes());
        if (diagram.getQuestionId()==null||diagram.getType()==null||diagram.getNum()==null)
        return  HttpMessage.invalidArgument();
        final int insert = diagramService.insert(diagram.init());
        return HttpMessage.ok("insert:"+insert);
    }

    @PostMapping("/imageUp")
    public String image(Integer questionId,Integer num,HttpServletRequest httpServletRequest, MultipartFile image, Model model) throws IOException {
        System.out.println("questionId:"+questionId);
        System.out.println("num:"+num);
        System.out.println("-------------------------------------------");

        System.out.println(image.getName());
        System.out.println(image.getContentType());
        System.out.println(image.getOriginalFilename());
        System.out.println(image.getSize());
        System.out.println("-------------------------------------------");
//        System.out.println(multipartFile.getBytes());

        Diagram diagram = new Diagram();
        diagram.setType(image.getContentType());
        diagram.setQuestionId(questionId);
        diagram.setNum(num);
        diagram.setImage(image.getBytes());
        diagram.init();

        final int insert = diagramService.insert(diagram);
        if (insert==1) model.addAttribute("info","成功插入一条数据");
        else model.addAttribute("info","插入数据时失败");

        model.addAttribute("type",diagram.getType());
        model.addAttribute("questionId",questionId);
        model.addAttribute("num",num);
        model.addAttribute("base64",diagram.getDiagram());
        return "diagram";
    }

    @GetMapping("/diagram/show")
    public String showDiagram(int questionId, int num, Model model){
        DiagramKey diagramKey = new DiagramKey();
        diagramKey.setQuestionId(questionId);
        diagramKey.setNum(num);
        final Diagram diagram = diagramService.selectByPrimaryKey(diagramKey);

        model.addAttribute("diagram",diagram);

        return "diagram_show";
    }
}
