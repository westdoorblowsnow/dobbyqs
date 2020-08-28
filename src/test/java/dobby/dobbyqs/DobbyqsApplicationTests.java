package dobby.dobbyqs;

import dobby.dobbyqs.backstage.bean.BackQuestion;
import dobby.dobbyqs.backstage.service.BackQuestionService;
import dobby.dobbyqs.csv.CsvQuestionBean;
import dobby.dobbyqs.csv.CsvUtil;
import dobby.dobbyqs.mybatis.mapper.*;
import dobby.dobbyqs.mybatis.pojo.*;
import dobby.dobbyqs.mybatis.service.ProfessionService;
import dobby.dobbyqs.mybatis.service.QuestionService;
import dobby.dobbyqs.web.DobbyUtils;
import dobby.dobbyqs.web.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class DobbyqsApplicationTests {

    @Test
    void contextLoads(@Autowired AdditionMapper additionMapper, @Autowired OptionsMapper optionsMapper, @Autowired QuestionMapper questionMapper, @Autowired PaperMapper paperMapper, @Autowired PaperQuestionLinkMapper paperQuestionLinkMapper) {
//        updateAddition(additionMapper);
//        updateOptions(optionsMapper);
//        csv(additionMapper, optionsMapper, questionMapper, paperMapper, paperQuestionLinkMapper);
    }

    String root = "C:\\Users\\pc\\Desktop\\中药学(士)\\";
    String name = "中药学(士) 提分卷";
    String[] nums = {"一", "二", "三", "四", "五", "六", "七"};
    String[] subjects = {"基础知识", "相关专业知识", "专业知识", "专业实践能力"};
    Integer[] subjectIds = {54, 55, 56, 57};


    @Transactional
    void csv(AdditionMapper additionMapper, OptionsMapper optionsMapper, QuestionMapper questionMapper, PaperMapper paperMapper, PaperQuestionLinkMapper paperQuestionLinkMapper) {
        List<Object> objects = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < subjects.length; j++) {
                List<CsvQuestionBean> csvData = null;
                try {
                    csvData = CsvUtil.getCsvData(root + name + nums[i] + " " + subjects[j] + ".csv", CsvQuestionBean.class);
                } catch (Exception e) {
                    objects.add(e);
                }
                if (csvData != null && csvData.size() == 100) {
                    Paper paper = new Paper();
                    paper.setName("2020 " + name + nums[i] + " " + subjects[j]);
                    paper.setTag("2020");
                    paper.setSubjectId(subjectIds[j]);
                    int i4 = paperMapper.insertSelective(paper);//插入试卷
                    System.out.println("插入试卷："+paper);
                    Question question = new Question();//待用题目
                    Addition addition = new Addition();//待用题干
                    Options options = new Options();//待用选项
                    for (int k = 0; k < csvData.size(); k++) {//遍历所有题目数据
                        CsvQuestionBean csvQuestionBean = csvData.get(k);
                        if (!StringUtils.isBlank(csvQuestionBean.getAddition())) {//题干不为空插入题干
                            addition.setId(null);
                            addition.setAddition(csvQuestionBean.getAddition());
                            int i1 = additionMapper.insertSelective(addition);
                            System.out.println("插入题干："+addition);
                        }

                        String[] ns = csvQuestionBean.getOptions().split("n");//分解选项
                        String op = DobbyUtils.stringOptions(Arrays.asList(ns));

                        if (op.equals(options.getOptions())) {//选项同上一题一样
                            if (question.getType().equals("A1")) {//上一题暂时存为A1
                                question.setType("B1");//改为B1

                                question.setAdditionId(null);
                                question.setOptionsId(null);
                                question.setTag(null);
                                question.setAnswer(null);
                                question.setExplain(null);
                                question.setSubjectId(null);
                                question.setQuestion(null);

                                int i2 = questionMapper.updateByPrimaryKeySelective(question);//跟新试题
                                System.out.println("将试题跟正为B1:"+question);
                            }
                            question.setId(null);
                            question.setQuestion(csvQuestionBean.getQuestion());//设置题目
                            question.setExplain(csvQuestionBean.getExplain());//设置解析
                            question.setAnswer(DobbyUtils.intAnswer(csvQuestionBean.getAnswer()));//设置答案
                            if (!StringUtils.isBlank(csvQuestionBean.getAddition())) {//如果有题干设置题干（题干已经插入了）
                                question.setAdditionId(addition.getId());
                            } else {
                                question.setAdditionId(null);
                            }
                            question.setOptionsId(options.getId());
                            question.setType("B1");
                            question.setTag("2020");
                            question.setSubjectId(paper.getSubjectId());

                            int i1 = questionMapper.insertSelective(question);
                            PaperQuestionLink paperQuestionLink = new PaperQuestionLink();
                            paperQuestionLink.setQuestionId(question.getId());
                            paperQuestionLink.setPaperId(paper.getId());
                            paperQuestionLink.setScore(1);
                            int i2 = paperQuestionLinkMapper.insertSelective(paperQuestionLink);
                        } else {
                            options.setId(null);
                            options.setOptions(op);
                            int i3 = optionsMapper.insertSelective(options);
                            System.out.println("插入选项："+options);

                            question.setId(null);
                            question.setTag("2020");
                            question.setType("A1");
                            question.setOptionsId(options.getId());
                            question.setQuestion(csvQuestionBean.getQuestion());
                            question.setSubjectId(paper.getSubjectId());
                            question.setExplain(csvQuestionBean.getExplain());
                            question.setAnswer(DobbyUtils.intAnswer(csvQuestionBean.getAnswer()));
                            if (!StringUtils.isBlank(csvQuestionBean.getAddition())) {
                                question.setAdditionId(addition.getId());
                            } else {
                                question.setAdditionId(null);
                            }
                            int i1 = questionMapper.insertSelective(question);
                            System.out.println("插入试题："+question);
                            PaperQuestionLink paperQuestionLink = new PaperQuestionLink();
                            paperQuestionLink.setQuestionId(question.getId());
                            paperQuestionLink.setPaperId(paper.getId());
                            paperQuestionLink.setScore(1);
                            int i2 = paperQuestionLinkMapper.insertSelective(paperQuestionLink);
                        }
                    }
                }
            }
        }
    }


    void updateAddition(AdditionMapper additionMapper) {
        List<Addition> additions = additionMapper.selectAll();
        for (Addition a : additions) {
            if (StringUtils.isBlank(a.getAddition())) {
                System.out.println("删除：" + "id:" + a.getId() + " 为空addition！");
                additionMapper.deleteByPrimaryKey(a.getId());
            } else {
                String s = StringUtils.trimToEmpty(a.getAddition());
                if (s.length() != a.getAddition().length()) {
                    a.setAddition(s);
                    additionMapper.updateByPrimaryKeySelective(a);
                    System.out.println("更新：" + a);
                }
            }
        }
    }

    public void updateOptions(OptionsMapper optionsMapper) {
        List<Options> options = optionsMapper.selectAll();
        int sum = 0;
        for (Options o : options) {
            List<String> strings = DobbyUtils.listOptions(o.getOptions());
            for (int i = 0; i < strings.size(); i++) {
                strings.set(i, strings.get(i).trim());
            }
            String options1 = DobbyUtils.stringOptions(strings);
            if (options1.length() != o.getOptions().length()) {
                o.setOptions(options1);
                optionsMapper.updateByPrimaryKeySelective(o);
                sum++;
            }
        }
        System.out.println(sum);
    }
}
