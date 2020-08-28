package dobby.dobbyqs.web.controller;

import dobby.dobbyqs.mybatis.pojo.Profession;
import dobby.dobbyqs.mybatis.service.ProfessionService;
import dobby.dobbyqs.web.DobbyUtils;
import dobby.dobbyqs.web.StringUtils;
import dobby.dobbyqs.web.WebBeanToPOJO;
import dobby.dobbyqs.web.bean.GetProfession;
import dobby.dobbyqs.web.HttpMessage;
import dobby.dobbyqs.web.bean.PostProfession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class ProfessionController {

    @Autowired
    ProfessionService professionService;

    @ResponseBody
    @GetMapping(path = "/get/profession/all")
    public HttpMessage getAllFullProfession() {
        final List<GetProfession> getProfessions = professionService.selectGetAll();
        if (getProfessions != null) return HttpMessage.get(getProfessions);
        else return HttpMessage.notFound();
    }

    @ResponseBody
    @PostMapping(path = "/post/profession/insert", consumes = "application/json", produces = "application/json")
    public HttpMessage insertPostProfession(@RequestBody PostProfession postProfession) {
        if (postProfession == null) return HttpMessage.invalidArgument();
//        if (StringUtils.isBlank(postProfession.getCode()))
//            return HttpMessage.invalidArgument("code:" + postProfession.getCode());
        String code = StringUtils.trimToNul(postProfession.getCode());
        postProfession.setCode(code);
        if (StringUtils.isBlank(postProfession.getName()))
            return HttpMessage.invalidArgument("name:" + postProfession.getName());
        final Profession profession = WebBeanToPOJO.toProfession(postProfession);
        final int insertProfession = professionService.insertSelective(profession);
        if (insertProfession == 1) return HttpMessage.insertOk(profession);
        else return HttpMessage.executeException("插入失败，数据已存在");
    }

    @ResponseBody
    @PostMapping(path = "/post/profession/update", consumes = "application/json", produces = "application/json")
    public HttpMessage updateProfessionByKey(@RequestBody Profession profession) {
        if (profession == null) return HttpMessage.invalidArgument();
        if (profession.getId() == null || profession.getId() < 0)
            return HttpMessage.invalidArgument("id:" + profession.getId());
        final int i = professionService.updateByPrimaryKeySelective(profession);
        if (i == 1) return HttpMessage.updateOK();
        else return HttpMessage.executeException("跟新失败，数据无改变或id错误");
    }

}
