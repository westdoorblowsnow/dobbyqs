package dobby.dobbyqs.mybatis.pojo;

import java.io.Serializable;
import java.util.StringJoiner;

public class Options implements Serializable {
    private Integer id;

    private String options;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Options.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("options='" + options + "'")
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
        Options other = (Options) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getOptions() == null ? other.getOptions() == null : this.getOptions().equals(other.getOptions()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOptions() == null) ? 0 : getOptions().hashCode());
        return result;
    }
}