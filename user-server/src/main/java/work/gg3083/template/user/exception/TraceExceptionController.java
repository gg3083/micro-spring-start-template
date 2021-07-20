package work.gg3083.template.user.exception;

import com.fasterxml.jackson.core.JsonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import work.gg3083.template.base.exception.MyException;
import work.gg3083.template.base.exception.MyExceptionType;
import work.gg3083.template.base.model.vo.JsonBack;

import java.util.List;
import java.util.Map;

/***
 *
 * 捕获异常
 * @author Gimi
 * @date 2019-06-19 21:37:07
 *
 ***/
@Configuration
@RestControllerAdvice("work.gg3083.template.user")
public class TraceExceptionController extends ResponseEntityExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(TraceExceptionController.class);

    public TraceExceptionController() {
        logger.info("init - TraceExceptionController");
    }


    /**
     * TODO 参数验证不生效 改用下面方法
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException exp, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String parameterName = exp.getParameterName();
        JsonBack jsonBack = new JsonBack(JsonBack.JSON_BACK_FAILED, MyExceptionType.VALIDATE_ERR.getErrorCode(), "参数" + parameterName + "输入有误", "missing.request.parameter:" + parameterName);
        return new ResponseEntity<>(jsonBack, HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exp, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.info("errorJson.Accept:{},Content-Type:{},exp:{}",new Object[]{request.getHeader("Accept"),request.getHeader("Content-Type"),exp==null?null:exp.getMessage()});
        logger.info("errorJson.exp",exp);

        JsonBack jsonBack = null;
        Map<String, Object> _err = null;

        if(exp instanceof MyException){
            jsonBack = new JsonBack((MyException)exp);

        }else if(exp instanceof MissingServletRequestParameterException) {
            String parameterName = ((MissingServletRequestParameterException) exp).getParameterName();
            jsonBack = new JsonBack(JsonBack.JSON_BACK_FAILED, MyExceptionType.VALIDATE_ERR.getErrorCode(), "参数" + parameterName + "输入有误", "missing.request.parameter:" + parameterName);

        }else if(exp instanceof HttpMessageNotReadableException) {
            String msg = ((HttpMessageNotReadableException) exp).getMessage();
            jsonBack = new JsonBack(JsonBack.JSON_BACK_FAILED, MyExceptionType.VALIDATE_ERR.getErrorCode(), msg, msg);

        } else if(exp instanceof MethodArgumentTypeMismatchException) {
            String msg = ((MethodArgumentTypeMismatchException) exp).getMessage();
            Object value = ((MethodArgumentTypeMismatchException) exp).getValue();
            String requiredType = ((MethodArgumentTypeMismatchException) exp).getRequiredType().toString();
            jsonBack = new JsonBack(JsonBack.JSON_BACK_FAILED, MyExceptionType.VALIDATE_ERR.getErrorCode(), String.format("值:%s 格式错误,期望类型为：%s", value, requiredType), msg);

        } else if (exp instanceof MethodArgumentNotValidException){
            String errMsg = "请求参数验证失败";
            // 获取异常信息
            BindingResult exceptions = ((MethodArgumentNotValidException)exp).getBindingResult();
            // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
            if (exceptions.hasErrors()) {
                List<ObjectError> errors = exceptions.getAllErrors();
                if (!errors.isEmpty()) {
                    // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                    FieldError fieldError = (FieldError) errors.get(0);

                    jsonBack = new JsonBack(JsonBack.JSON_BACK_FAILED, MyExceptionType.VALIDATE_ERR.getErrorCode(),  fieldError.getField() + fieldError.getDefaultMessage(),  fieldError.getCodes());

                }
            }
        } else{
            String msgVal = null;
            try {
                msgVal = exp.getCause()==null?exp.getMessage():exp.getCause().getMessage();
            } catch (Exception e) {
                jsonBack = new JsonBack(JsonBack.JSON_BACK_FAILED, MyExceptionType.VALIDATE_ERR.getErrorCode(), msgVal, exp.getMessage());
            }
            logger.error("errorJson.errdata is:{}", _err);
        }

        return new ResponseEntity<>(jsonBack,HttpStatus.OK);

    }

}