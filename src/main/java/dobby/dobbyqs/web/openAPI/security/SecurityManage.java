package dobby.dobbyqs.web.openAPI.security;

public interface SecurityManage {
    boolean checkToken();
    boolean checkTimeStamp();
    boolean checkTimes();
}
