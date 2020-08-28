package dobby.dobbyqs.mybatis.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Base64;
import java.util.StringJoiner;

/**
 * diagram
 * @author 
 */
public class Diagram extends DiagramKey implements Serializable {
    private String diagram;
    @JsonIgnore
    private byte[] image;

    public Diagram init(){
        diagram = Base64.getEncoder().encodeToString(image);
        return this;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    private String type;

    private static final long serialVersionUID = 1L;

    public String getDiagram() {
        return diagram;
    }

    public void setDiagram(String diagram) {
        this.diagram = diagram;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        Diagram other = (Diagram) that;
        return (this.getQuestionId() == null ? other.getQuestionId() == null : this.getQuestionId().equals(other.getQuestionId()))
            && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()))
            && (this.getDiagram() == null ? other.getDiagram() == null : this.getDiagram().equals(other.getDiagram()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getQuestionId() == null) ? 0 : getQuestionId().hashCode());
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        result = prime * result + ((getDiagram() == null) ? 0 : getDiagram().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Diagram.class.getSimpleName() + "[", "]")
                .add("diagram='" + diagram + "'")
                .add("image=" + Arrays.toString(image))
                .add("type='" + type + "'")
                .toString();
    }
}