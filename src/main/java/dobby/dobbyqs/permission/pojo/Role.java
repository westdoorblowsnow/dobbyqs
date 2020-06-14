package dobby.dobbyqs.permission.pojo;

import java.util.List;
import java.util.Objects;

/**
 * 角色类
 */
public class Role {
    Integer id;
    String name;
    String info;
    Boolean ban;
    List<Permission> permissions;

    public Role(Integer id, String name, String info, Boolean ban, List<Permission> permissions) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.ban = ban;
        this.permissions = permissions;
    }

    public Role() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Role{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", info='").append(info).append('\'');
        sb.append(", ban=").append(ban);
        sb.append(", permissions=").append(permissions);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return Objects.equals(getId(), role.getId()) &&
                Objects.equals(getName(), role.getName()) &&
                Objects.equals(getPermissions(), role.getPermissions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPermissions());
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

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
