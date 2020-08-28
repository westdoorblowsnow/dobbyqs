package dobby.dobbyqs.paper;

import dobby.dobbyqs.mybatis.mapper.PaperMapper;
import dobby.dobbyqs.paper.planB.PlanBPaperParser;
import dobby.dobbyqs.web.HttpMessage;
import dobby.dobbyqs.web.bean.PostPaper;
import dobby.dobbyqs.web.bean.PostQuestion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class PaperParserController {

    @ResponseBody
    @PostMapping(path = "/paperparser", consumes = "application/json", produces = "application/json")
    public HttpMessage postPaper(@RequestBody AA aa) {
        PaperParse paperParse = new PlanBPaperParser();

        String innerPaper = aa.paper.replaceAll("（", "(").replaceAll("）", ")").replaceAll("．", ".");
        String innerAnswer = aa.answer.replaceAll("（", "(").replaceAll("）", ")").replaceAll("．", ".");
        PostPaper postPaper = null;
        try {
            postPaper = paperParse.parse(innerPaper, innerAnswer);
            //-----------------------------------
//            System.out.println(postPaper);
//            return new HttpMessage(HttpMessage.PAPER_PARSER_EXCEPTION, "识别成功！");
            //-----------------------------------
        } catch (PaperParserException e) {
            return new HttpMessage(HttpMessage.PAPER_PARSER_EXCEPTION, e.getMessage());
        }
        postPaper.setSubjectId(aa.subjectId);
        postPaper.setName(aa.name);
        postPaper.setTag(aa.tag);
        List<PostQuestion> questions = postPaper.getQuestions();
        for (PostQuestion postQuestion : questions) {
            postQuestion.setSubjectId(aa.subjectId);
            postQuestion.setTag(aa.tag);
            List<String> options = postQuestion.getOptions();
            for (int i = 0; i < options.size(); i++) {
                options.set(i,options.get(i).trim());
            }
        }
        return HttpMessage.ok("识别出" + questions.size() + "个题目", postPaper);
    }

    @GetMapping("/PaperParser1")
    public String ttt(){
        return "ParserPaper";
    }


    @GetMapping("/show")
    public String hhh() {
        return "show_Questions";
    }

    static class AA {
        String answer;
        String paper;
        String name;
        Integer subjectId;
        String tag;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(Integer subjectId) {
            this.subjectId = subjectId;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public AA(String answer, String paper, String name, Integer subjectId, String tag) {
            this.answer = answer;
            this.paper = paper;
            this.name = name;
            this.subjectId = subjectId;
            this.tag = tag;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getPaper() {
            return paper;
        }

        public void setPaper(String paper) {
            this.paper = paper;
        }

        public AA(String answer, String paper) {
            this.answer = answer;
            this.paper = paper;
        }

        public AA() {
        }
    }
}
