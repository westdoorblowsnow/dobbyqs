package dobby.dobbyqs.web.controller;

import dobby.dobbyqs.mybatis.pojo.Subject;
import dobby.dobbyqs.mybatis.service.SubjectService;
import dobby.dobbyqs.web.StringUtils;
import dobby.dobbyqs.web.WebBeanToPOJO;
import dobby.dobbyqs.web.HttpMessage;
import dobby.dobbyqs.web.bean.PostSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SubjectController {

    @Autowired
    SubjectService subjectService;


    @ResponseBody
    @PostMapping(path = "/post/subject/insert", consumes = "application/json", produces = "application/json")
    public HttpMessage insertSubject(@RequestBody PostSubject postSubject) {
        if (postSubject == null) return HttpMessage.invalidArgument(postSubject.toString());
        if (postSubject.getProfessionId() == null || postSubject.getProfessionId() < 0)
            return HttpMessage.invalidArgument("professionId=" + postSubject.getProfessionId());
        if (StringUtils.isBlank(postSubject.getName())) return HttpMessage.invalidArgument("name="+postSubject.getName());
        final Subject subject = WebBeanToPOJO.toSubject(postSubject);
        final int insertSubject = subjectService.insertSelective(subject);
        if (insertSubject == 1) return HttpMessage.insertOk();
        else return HttpMessage.executeException("插入失败").setData(subject);
    }

    @ResponseBody
    @PostMapping(path = "/post/subject/update", consumes = "application/json", produces = "application/json")
    public HttpMessage updateSubjectById(@RequestBody Subject subject) {
        if (subject == null) return HttpMessage.invalidArgument();
        if (subject.getId() == null || subject.getId() < 0) HttpMessage.invalidArgument("id");
        final int i = subjectService.updateByPrimaryKeySelective(subject);
        if (i == 1) return HttpMessage.updateOK();
        else return HttpMessage.executeException("修改失败").setData(subject);
    }

   @GetMapping("/ProfessionSubject")
    public String professionAndSubject(){
        return "Profession_Subject";
    }

}
