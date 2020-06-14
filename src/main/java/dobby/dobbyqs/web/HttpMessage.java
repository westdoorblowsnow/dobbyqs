package dobby.dobbyqs.web;

public class HttpMessage {
    public static final Integer REQUEST_SUCCESS = new Integer(1);
    public static final Integer REQUEST_EXCEPTION = new Integer(-4);
    public static final Integer INVALID_PARAMETER = new Integer(-1);
    public static final Integer EXECUTE_EXCEPTION = new Integer(-2);
    public static final Integer DATABASE_EXCEPTION = new Integer(-3);
    public static final Integer PAPER_PARSER_EXCEPTION = new Integer(-3);

    Integer status;
    String info;
    Object data;

    public static HttpMessage requestException(String info) {return new HttpMessage(REQUEST_EXCEPTION, info);}

    public static HttpMessage ok(String info) {
        return new HttpMessage(REQUEST_SUCCESS, info);
    }

    public static HttpMessage ok(String info, Object data) {
        return new HttpMessage(REQUEST_SUCCESS, info, data);
    }

    public static HttpMessage databaseException(String info) {
        return new HttpMessage(DATABASE_EXCEPTION, info);
    }

    public static HttpMessage executeException(String info) {
        return new HttpMessage(EXECUTE_EXCEPTION, info);
    }

    public static HttpMessage get(Object data) {
        return new HttpMessage(REQUEST_SUCCESS, "取得数据", data);
    }

    public static HttpMessage notFound() {
        return new HttpMessage(EXECUTE_EXCEPTION, "找不到资源");
    }

    public static HttpMessage invalidArgument() {
        return new HttpMessage(INVALID_PARAMETER, "非法参数");
    }

    public static HttpMessage invalidArgument(String info) {
        return new HttpMessage(INVALID_PARAMETER, "非法参数" + (info == null ? "" : "[" + info + "]"));
    }

    public static HttpMessage insertOk() {
        return new HttpMessage(REQUEST_SUCCESS, "插入成功");
    }

    public static HttpMessage insertOk(Object data) {
        return new HttpMessage(REQUEST_SUCCESS, "插入成功").setData(data);
    }

    public static HttpMessage updateOK() {
        return new HttpMessage(REQUEST_SUCCESS, "跟新成功");
    }

    public static HttpMessage deleteOK() {
        return new HttpMessage(REQUEST_SUCCESS, "删除成功");
    }

    public Integer getStatus() {
        return status;
    }

    public HttpMessage setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Object getData() {
        return data;
    }

    public HttpMessage setData(Object data) {
        this.data = data;
        return this;
    }

    public HttpMessage(Integer status, String info, Object data) {
        this.status = status;
        this.info = info;
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public HttpMessage setInfo(String info) {
        this.info = info;
        return this;
    }

    public HttpMessage appendInfo(String info) {
        this.info = info == null ? info : this.info + info;
        return this;
    }

    public HttpMessage() {
    }

    public HttpMessage(Integer status, String info) {
        this.status = status;
        this.info = info;
    }
}
