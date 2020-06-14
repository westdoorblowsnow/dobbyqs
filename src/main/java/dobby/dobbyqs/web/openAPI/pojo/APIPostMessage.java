package dobby.dobbyqs.web.openAPI.pojo;

public class APIPostMessage {
    public static final int CODE_LITTLE_QUESTION = 1000;
    public static final int CODE_ANSWER_AND_EXPLAIN = 1001;


    int code;
    String name;
    String password;
    String token;
    String timeStamp;
    Integer questionId;
    Integer paperId;
    Integer subjectId;
    Integer professionId;


    public static boolean isValid(APIPostMessage apiPostMessage) {
        if (apiPostMessage == null) return false;
        switch (apiPostMessage.code) {
            case CODE_LITTLE_QUESTION:
                if (apiPostMessage.questionId == null) return false;
            case CODE_ANSWER_AND_EXPLAIN:
                if (apiPostMessage.questionId == null) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("APIPostMessage{");
        sb.append("code=").append(code);
        sb.append(", name='").append(name).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", token='").append(token).append('\'');
        sb.append(", timeStamp='").append(timeStamp).append('\'');
        sb.append(", questionId=").append(questionId);
        sb.append(", paperId=").append(paperId);
        sb.append(", subjectId=").append(subjectId);
        sb.append(", professionId=").append(professionId);
        sb.append('}');
        return sb.toString();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Integer professionId) {
        this.professionId = professionId;
    }
}
