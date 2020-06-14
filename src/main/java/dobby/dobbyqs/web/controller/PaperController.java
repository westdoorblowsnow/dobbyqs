package dobby.dobbyqs.web.controller;

import dobby.dobbyqs.mybatis.pojo.Diagram;
import dobby.dobbyqs.mybatis.pojo.DiagramKey;
import dobby.dobbyqs.mybatis.pojo.Question;
import dobby.dobbyqs.mybatis.service.DiagramService;
import dobby.dobbyqs.mybatis.service.PaperService;
import dobby.dobbyqs.paper_parse.PaperParse;
import dobby.dobbyqs.paper_parse.PaperParser;
import dobby.dobbyqs.web.HttpMessage;
import dobby.dobbyqs.web.StringUtils;
import dobby.dobbyqs.web.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class PaperController {
    @Autowired
    PaperService paperService;

    @Autowired
    DiagramService diagramService;

    /**
     * 上传试卷套题
     *
     * @param postPaper
     * @return
     */
    @ResponseBody
    @PostMapping(path = "/post/paper/insert/full", consumes = "application/json", produces = "application/json")
    public HttpMessage postFullPaper(@RequestBody PostPaper postPaper) {
        if (postPaper == null) return HttpMessage.invalidArgument();
        if (postPaper.getSubjectId() == null || postPaper.getSubjectId() <= 0 || StringUtils.isBlank(postPaper.getName()) || postPaper.getQuestions() == null || postPaper.getQuestions().isEmpty())
            return HttpMessage.invalidArgument();
        for (PostQuestion postQuestion :
                postPaper.getQuestions()) {
            if (postQuestion == null) return HttpMessage.invalidArgument();
        }
        try {
            paperService.insertPostPaper(postPaper);
        } catch (Exception e) {
            e.printStackTrace();
            return new HttpMessage(-1, "paper服务异常！", e.getStackTrace());
        }
        return new HttpMessage(1, "paperFull inserted");
    }

    @GetMapping("/paper")
    public String paper(Integer id, Model model) {
        final GetPaper paper = paperService.getPaperQuestionsById(id);
        model.addAttribute("name", paper.getName());
        model.addAttribute("profession", paper.getProfession());
        model.addAttribute("subject", paper.getSubject());
        model.addAttribute("tag", paper.getTag());
        model.addAttribute("id", paper.getId());
        model.addAttribute("A12", PaperParser.A1_TYPE_TITLE);
        model.addAttribute("A34", PaperParser.A3_TYPE_TITLE);
        model.addAttribute("B", PaperParser.B1_TYPE_TITLE);
        final List<GetQuestion> questions = paper.getQuestions();
        List<GetQuestion> A12s = new ArrayList<>();
        List<String> A34s = new ArrayList<>();
        List<String> Bs = new ArrayList<>();
        Map<String, List<GetQuestion>> groupedQuestion = new HashMap<>();

        Map<String, Diagram> questionDiagram = new HashMap<>();
        Map<String,Diagram> additionDiagram=new HashMap<>();
        Map<String,Diagram> optionsADiagram = new HashMap<>();
        Map<String,Diagram> optionsBDiagram = new HashMap<>();
        Map<String,Diagram> optionsEDiagram = new HashMap<>();
        Map<String,Diagram> optionsCDiagram = new HashMap<>();
        Map<String,Diagram> optionsDDiagram = new HashMap<>();
        final Pattern pattern = Pattern.compile("\\$\\{img:\\d+\\}");
        final Pattern pattern1 = Pattern.compile("\\d+");
        for (GetQuestion question:
             questions) {
            final Matcher matcher = pattern.matcher(question.getQuestion());
            if (matcher.find()) {
                question.setQuestion(question.getQuestion().replaceAll("\\$\\{img:\\d+\\}", ""));
                DiagramKey diagramKey = new DiagramKey();
                final Matcher matcher1 = pattern1.matcher(matcher.group());
                matcher1.find();
                diagramKey.setQuestionId(question.getId());
                diagramKey.setNum(Integer.parseInt(matcher1.group()));
                questionDiagram.put(question.getId()+"",diagramService.selectByPrimaryKey(diagramKey));
            }
        }

        model.addAttribute("questionDiagram",questionDiagram);


//        System.out.println("all questions: "+questions.size());
        for (GetQuestion question :
                questions) {
            if (question.getType().matches("A[12]")) A12s.add(question);
            else if (question.getType().matches("A[34]")) {
                if (!A34s.contains(question.getGroup()))
                    A34s.add(question.getGroup());
                if (groupedQuestion.containsKey(question.getGroup())) groupedQuestion.get(question
                        .getGroup()).add(question);
                else {
                    ArrayList<GetQuestion> getQuestions = new ArrayList<>();
                    groupedQuestion.put(question.getGroup(), getQuestions);
                    getQuestions.add(question);
                }
            } else if (question.getType().matches("B1")) {
                if (!Bs.contains(question.getGroup()))
                    Bs.add(question.getGroup());
                if (groupedQuestion.containsKey(question.getGroup())) groupedQuestion.get(question
                        .getGroup()).add(question);
                else {
                    ArrayList<GetQuestion> getQuestions = new ArrayList<>();
                    groupedQuestion.put(question.getGroup(), getQuestions);
                    getQuestions.add(question);
                }
            }
        }
//        System.out.println("A12s questions: "+A12s.size());
//        System.out.println("A34s questions: "+A34s.size());
//        System.out.println("Bs questions: "+Bs.size());
        model.addAttribute("A12s", A12s);
        model.addAttribute("A34s", A34s);
        model.addAttribute("Bs", Bs);
        model.addAttribute("groupedQuestion", groupedQuestion);
        model.addAttribute("num", new Num());
        return "paper";
    }

    @GetMapping("/paper/list")
    public String paperList(Model model) {
        final List<Map<String, Object>> allPapers = paperService.getAllPapers();
        allPapers.sort(new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                final int compareTo = ((String) o1.get("code")).compareTo((String) o2.get("code"));
                if (compareTo != 0) return compareTo;
                else return ((String) o1.get("subjectName")).compareTo((String) o2.get("subjectName"));
            }
        });
        model.addAttribute("papers", allPapers);
        return "PaperList";
    }

}
