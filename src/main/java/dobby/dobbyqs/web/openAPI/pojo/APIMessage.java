package dobby.dobbyqs.web.openAPI.pojo;

import java.util.Objects;

public class APIMessage {
    public static final int CODE_NOT_FOUND = 100;
    public static final int CODE_OK = 200;
    public static final int CODE_NO_ACCESS = 300;
    public static final int CODE_SERVER_ERROR = 500;
    public static final int CODE_PARAMETER_INVALID = 600;

    private int code;
    private String message;
    private Object info;
    private Object data;

    public static APIMessage notFound(String message, Object info, Object data) {
        return new APIMessage(CODE_NOT_FOUND, message, info, data);
    }

    public static APIMessage ok(String message, Object info, Object data) {
        return new APIMessage(CODE_OK, message, info, data);
    }

    public static APIMessage noAccess(String message, Object info, Object data) {
        return new APIMessage(CODE_NO_ACCESS, message, info, data);
    }

    public static APIMessage serverError(String message, Object info, Object data) {
        return new APIMessage(CODE_SERVER_ERROR, message, info, data);
    }

    public static APIMessage parameterInvalid() {
        return new APIMessage(CODE_PARAMETER_INVALID, "请求参数错误!", null, null);
    }

    public static APIMessage parameterInvalid(String message){
        return new APIMessage(CODE_PARAMETER_INVALID, message, null, null);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("APIMessage{");
        sb.append("code=").append(code);
        sb.append(", message='").append(message).append('\'');
        sb.append(", info=").append(info);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof APIMessage)) return false;
        APIMessage that = (APIMessage) o;
        return Objects.equals(getCode(), that.getCode()) &&
                Objects.equals(getMessage(), that.getMessage()) &&
                Objects.equals(getInfo(), that.getInfo()) &&
                Objects.equals(getData(), that.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getMessage(), getInfo(), getData());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public APIMessage(Integer code, String message, Object info, Object date) {
        this.code = code;
        this.message = message;
        this.info = info;
        this.data = date;
    }

    public APIMessage() {
    }
}
