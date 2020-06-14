package dobby.dobbyqs.web.openAPI.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LittleQuestion {
    private Integer id;
    private String question;
    private String addition;
    private List<String> options;

    @JsonIgnore
    private String optionsS;

    public String getOptionsS() {
        return optionsS;
    }

    public void setOptionsS(String optionsS) {
        this.optionsS = optionsS;
    }

    public LittleQuestion init(){
        if (optionsS==null) throw new RuntimeException();
        options = new ArrayList<>(Arrays.asList(optionsS.split("##")));
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Question{");
        sb.append("id=").append(id);
        sb.append(", question='").append(question).append('\'');
        sb.append(", options=").append(options);
        sb.append('}');
        return sb.toString();
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public LittleQuestion(Integer id, String question, List<String> options, Integer subjectId, Integer professionId, String subject, String profession) {
        this.id = id;
        this.question = question;
        this.options = options;
    }

    public LittleQuestion() {
    }
}
