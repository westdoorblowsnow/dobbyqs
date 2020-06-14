package dobby.dobbyqs.mybatis.pojo;

import java.io.Serializable;

/**
 * diagram
 * @author 
 */
public class DiagramKey implements Serializable {
    private Integer questionId;

    private Integer num;

    private static final long serialVersionUID = 1L;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DiagramKey other = (DiagramKey) that;
        return (this.getQuestionId() == null ? other.getQuestionId() == null : this.getQuestionId().equals(other.getQuestionId()))
            && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getQuestionId() == null) ? 0 : getQuestionId().hashCode());
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", questionId=").append(questionId);
        sb.append(", num=").append(num);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}