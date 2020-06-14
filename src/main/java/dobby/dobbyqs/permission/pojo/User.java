package dobby.dobbyqs.permission.pojo;

import java.util.List;
import java.util.Objects;

public class User {
    Integer id;
    String name;
    String info;
    Boolean ban;
    List<Role> roles;

    public User(Integer id, String name, String info, Boolean ban, List<Role> roles) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.ban = ban;
        this.roles = roles;
    }

    public User() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", info='").append(info).append('\'');
        sb.append(", ban=").append(ban);
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getRoles(), user.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getRoles());
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
