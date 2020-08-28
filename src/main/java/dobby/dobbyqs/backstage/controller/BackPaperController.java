package dobby.dobbyqs.backstage.controller;

import dobby.dobbyqs.backstage.bean.BackQuestion;
import dobby.dobbyqs.backstage.bean.ImgHelper;
import dobby.dobbyqs.backstage.service.BackPaperService;
import dobby.dobbyqs.mybatis.pojo.Paper;
import dobby.dobbyqs.mybatis.pojo.Profession;
import dobby.dobbyqs.paper.PaperParse;
import dobby.dobbyqs.web.bean.Num;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.*;

@Controller
public class BackPaperController {

    @Resource
    BackPaperService service;


    @GetMapping("/backstage/paper.html")
    public String paper(Integer id, Integer index, Integer move, Model model) {
        Paper paper = null;
        if (Objects.nonNull(id)) {
            paper = service.getPaperById(id);
        } else {
            if (Objects.isNull(index) || index < 0) index = 0;
            paper = service.getPaperByIndex(index);
        }
        model.addAttribute("index", index);
        model.addAttribute("id", id);
        if (Objects.nonNull(paper)) {
            final Map<String, List<BackQuestion>> map = service.getQuestionsByPaperId(paper.getId());
            if (map.containsKey("A1")) {
//                map.get("A1").sort(((o1, o2) -> {
//                    if (o1 == null) return -1;
//                    if (o2 == null) return 1;
//                    return o1.hashCode() - o2.hashCode();
//                }));
                model.addAttribute("A1", map.get("A1"));
            }
            if (map.containsKey("A3")) {
                final List<BackQuestion> a3 = map.get("A3");
                List<List<BackQuestion>> A3 = new ArrayList<>(3);
                A3.hashCode();
                HashMap<Integer, List<BackQuestion>> hashMap = new HashMap<>();
                for (int i = 0; i < a3.size(); i++) {
                    if (hashMap.containsKey(a3.get(i).getQuestion().getAdditionId())) {
                        hashMap.get(a3.get(i).getQuestion().getAdditionId()).add(a3.get(i));
                    } else {
                        hashMap.put(a3.get(i).getQuestion().getAdditionId(), new ArrayList<>());
                        hashMap.get(a3.get(i).getQuestion().getAdditionId()).add(a3.get(i));
                    }
                }
                final Set<Map.Entry<Integer, List<BackQuestion>>> entries = hashMap.entrySet();
                for (Map.Entry<Integer, List<BackQuestion>> entry : entries) {
                    A3.add(entry.getValue());
                }
                A3.sort((o1, o2) -> {
                    if (o1 == null) return -1;
                    if (o2 == null) return 1;
                    return o1.get(0).getQuestion().getAdditionId().compareTo(o2.get(0).getQuestion().getAdditionId());
                });
                model.addAttribute("A3", A3);
            } else {
                model.addAttribute("A3", null);
            }
            if (map.containsKey("B1")) {
                final List<BackQuestion> b1 = map.get("B1");
                List<List<BackQuestion>> B1 = new ArrayList<>(3);
                HashMap<Integer, List<BackQuestion>> hashMap = new HashMap<>();
                for (int i = 0; i < b1.size(); i++) {
                    if (!hashMap.containsKey(b1.get(i).getQuestion().getOptionsId())) {
                        hashMap.put(b1.get(i).getQuestion().getOptionsId(), new ArrayList<>());
                    }
                    hashMap.get(b1.get(i).getQuestion().getOptionsId()).add(b1.get(i));
                }
                final Set<Map.Entry<Integer, List<BackQuestion>>> entries = hashMap.entrySet();
                for (Map.Entry<Integer, List<BackQuestion>> entry : entries) {
                    B1.add(entry.getValue());
                }
                B1.sort((o1, o2) -> {
                    if (o1 == null) return -1;
                    if (o2 == null) return 1;
                    return o1.get(0).getQuestion().getOptionsId().compareTo(o2.get(0).getQuestion().getOptionsId());
                });
                model.addAttribute("B1", B1);
            } else {
                model.addAttribute("B1", null);
            }
        }
        model.addAttribute("paper", paper);
        model.addAttribute("imgHelper", new ImgHelper());
        model.addAttribute("QuestionA1", PaperParse.A1_TYPE_TITLE);
        model.addAttribute("QuestionA3", PaperParse.A3_TYPE_TITLE);
        model.addAttribute("QuestionB1", PaperParse.B1_TYPE_TITLE);
        model.addAttribute("numHelper", new Num());
        return "backstage/updatablePaper";
    }

    @GetMapping("/backstage/paperList.html")
    public String paperList(Integer professionId, Model model) {
        List<Paper> papers = null;
        String headProfession = null;
        if (professionId == null) {
            papers = service.getAllPaper();
        } else {
            papers = service.getPapersByProfessionId(professionId);
            Profession profession = service.getProfessionByProfessionId(professionId);
            if (Objects.nonNull(profession)) {
                headProfession = profession.getName() + "<" + profession.getCode() + ">";
            }
        }
        model.addAttribute("papers", papers);
        model.addAttribute("profession", headProfession);
        return "/backstage/paperList";
    }
}
