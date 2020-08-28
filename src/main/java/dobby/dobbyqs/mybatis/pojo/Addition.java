package dobby.dobbyqs.mybatis.pojo;

import java.io.Serializable;
import java.util.StringJoiner;

public class Addition implements Serializable {
    private Integer id;

    private String addition;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Addition.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("addition='" + addition + "'")
                .toString();
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
        Addition other = (Addition) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getAddition() == null ? other.getAddition() == null : this.getAddition().equals(other.getAddition()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAddition() == null) ? 0 : getAddition().hashCode());
        return result;
    }
}