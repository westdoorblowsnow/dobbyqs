package dobby.dobbyqs.web;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class CommonExceptionHandler {

    @ResponseBody
    @ExceptionHandler(MyBatisSystemException.class)
    public HttpMessage communicationsExceptionHandler(MyBatisSystemException e) {
        return HttpMessage.databaseException(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public HttpMessage requestException(org.springframework.http.converter.HttpMessageNotReadableException e) {
        return HttpMessage.requestException(e.getMessage());
    }
    @ResponseBody
    @ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
    public HttpMessage httpRequestMethodNotSupportedException(org.springframework.web.HttpRequestMethodNotSupportedException e){
        return HttpMessage.requestException(e.getMessage());
    }
}
