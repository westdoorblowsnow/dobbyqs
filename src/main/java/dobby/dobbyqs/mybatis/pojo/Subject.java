package dobby.dobbyqs.mybatis.pojo;

import java.io.Serializable;
import java.util.StringJoiner;

public class Subject implements Serializable {
    private Integer id;

    private Integer professionId;

    private String name;

    private static final long serialVersionUID = 1L;

    public Subject(Integer id, Integer professionId, String name) {
        this.id = id;
        this.professionId = professionId;
        this.name = name;
    }

    public Subject() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Integer professionId) {
        this.professionId = professionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Subject.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("professionId=" + professionId)
                .add("name='" + name + "'")
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
        Subject other = (Subject) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getProfessionId() == null ? other.getProfessionId() == null : this.getProfessionId().equals(other.getProfessionId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProfessionId() == null) ? 0 : getProfessionId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }
}