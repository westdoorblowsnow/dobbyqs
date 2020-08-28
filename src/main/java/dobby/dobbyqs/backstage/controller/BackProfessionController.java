package dobby.dobbyqs.backstage.controller;

import dobby.dobbyqs.backstage.bean.BackProfession;
import dobby.dobbyqs.backstage.service.BackProfessionService;
import dobby.dobbyqs.mybatis.pojo.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class BackProfessionController {

    @Resource
    BackProfessionService backProfessionService;

    @GetMapping("/backstage/professions")
    public String professionList(Model model) {
        final List<BackProfession> allBackProfessions = backProfessionService.getAllBackProfessions();
        allBackProfessions.sort(((o1, o2) -> {
            if (Objects.isNull(o1.getProfession().getCode())){
                return -1;
            }
            if (Objects.isNull(o2.getProfession().getCode())){
                return 1;
            }
            return o1.getProfession().getCode().compareTo(o2.getProfession().getCode());
        }));
        model.addAttribute("allBackProfessions", allBackProfessions);
        return "backstage/updatableProfession";
    }

    @ResponseBody()
    @PostMapping(value = "/backstage/profession/insert", produces = "application/json;utf-8", consumes = "application/json;utf-8")
    public Map<String, Object> addProfession(BackProfession backProfession) {
        Map<String, Object> map = new HashMap<>();
        if (backProfession == null) {
            map.put("code", 0);
            return map;
        } else {
            if (backProfession.getProfession() == null || backProfession.getSubjects() == null) {
                map.put("code", 0);
                return map;
            } else if (backProfession.getProfession().getCode() == null || backProfession.getProfession().getName() == null) {
                map.put("code", 0);
                return map;
            } else {
                final List<Subject> subjects = backProfession.getSubjects();
                for (Subject subject : subjects) {
                    if (subject == null || subject.getName() == null) {
                        map.put("code", 0);
                        return map;
                    }
                }
            }
        }
        try {
            final int i = backProfessionService.insertBackProfession(backProfession);
            if (i != 1) map.put("code", 0);
            else map.put("code", 1);
        } catch (Exception e) {
            map.put("code", 0);
        }
        return map;
    }


}
