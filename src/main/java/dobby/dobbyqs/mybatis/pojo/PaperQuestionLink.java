package dobby.dobbyqs.mybatis.pojo;

import java.io.Serializable;

public class PaperQuestionLink implements Serializable {
    private Integer paperId;

    private Integer questionId;

    private Integer score;

    private static final long serialVersionUID = 1L;

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", paperId=").append(paperId);
        sb.append(", questionId=").append(questionId);
        sb.append(", score=").append(score);
        sb.append("]");
        return sb.toString();
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
        PaperQuestionLink other = (PaperQuestionLink) that;
        return (this.getPaperId() == null ? other.getPaperId() == null : this.getPaperId().equals(other.getPaperId()))
                && (this.getQuestionId() == null ? other.getQuestionId() == null : this.getQuestionId().equals(other.getQuestionId()))
                && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPaperId() == null) ? 0 : getPaperId().hashCode());
        result = prime * result + ((getQuestionId() == null) ? 0 : getQuestionId().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        return result;
    }
}