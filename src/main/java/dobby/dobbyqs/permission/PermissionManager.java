package dobby.dobbyqs.permission;

public interface PermissionManager {
    public String[] getPermissionsByUserName(String userName);
    public String[] getPermissionsByUserId(Integer userId);
    public String getPermissionById(Integer id);
    public int createPermission();
    public int updatePermission();
    public int deletePermission();
    public boolean banPermission(Integer permissionId);
    public boolean banPermission(String permissionName);
}
