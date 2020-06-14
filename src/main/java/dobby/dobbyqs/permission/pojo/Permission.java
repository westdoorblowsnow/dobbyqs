package dobby.dobbyqs.permission.pojo;

import java.util.Objects;

/**
 * 权限类
 */
public class Permission {
    Integer id;
    String name;
    String info;
    Boolean ban;

    public Permission(Integer id, String name, String info, Boolean ban) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.ban = ban;
    }

    public Permission() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Permission{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", info='").append(info).append('\'');
        sb.append(", ban=").append(ban);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Permission)) return false;
        Permission that = (Permission) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Boolean getBan() {
        return ban;
    }

    public void setBan(Boolean ban) {
        this.ban = ban;
    }
}
