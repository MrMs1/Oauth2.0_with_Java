package oauth.demoproject.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Aspect
@Component
public class AopLogging {

    private static final Logger requestLogger = LoggerFactory.getLogger("output.request.body");
    private static final Logger responseLogger = LoggerFactory.getLogger("output.response.body");
    // private static final Logger defaultLogger =
    // LoggerFactory.getLogger(AopLogging.class);

    private StringBuilder getLogFileInputSb(String methodName, String valueType) {
        StringBuilder logInput = new StringBuilder();
        logInput.append("methodName: ");
        logInput.append(methodName);
        logInput.append("\r\n");
        logInput.append(valueType);
        logInput.append("\r\n");
        return logInput;
    }

    @Before(value = "execution(public * oauth.demoproject.app.ctrl.*Controller.*(..)) && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void outPutRequestValue(JoinPoint jp) {
        // メソッドの引数の名前
        final String[] methodArgNames = ((CodeSignature) jp.getSignature()).getParameterNames();

        // メソッドの引数の値
        final Object[] methodArgValues = jp.getArgs();

        final StringBuilder logInput = getLogFileInputSb(jp.getSignature().getName(), "RequestValue:");

        // 「メソッドの引数の名前=メソッドの引数の値」形式で表示する
        for (int i = 0; i < methodArgNames.length; i++) {
            logInput.append("param: ");
            logInput.append(methodArgNames[i]);
            logInput.append(", value: ");
            logInput.append(String.valueOf(methodArgValues[i]));
            logInput.append("\r\n");
        }
        requestLogger.info(logInput.toString());
    }

    @Before(value = "execution(public * oauth.demoproject.app.ctrl.*Controller.*(..)) && @annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void outReturnValue(JoinPoint jp) {

        final StringBuilder logInput = getLogFileInputSb(jp.getSignature().getName(), "RequestValue:");

        // メソッドの引数の値
        Object[] methodArgValues = jp.getArgs();

        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT); // jsonを整形
        try {
            logInput.append(mapper.writeValueAsString(methodArgValues[0]));
            requestLogger.info(logInput.toString());
        } catch (JsonProcessingException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }

    @AfterReturning(value = "execution(public * oauth.demoproject.app.ctrl.Controller.*(..))", returning = "returnValue")
    public void outReturnValue(JoinPoint jp, Object returnValue) {

        final StringBuilder logInput = getLogFileInputSb(jp.getSignature().getName(), "ResponseValue:");

        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT); // jsonを整形

        try {
            logInput.append(mapper.writeValueAsString(returnValue));
            responseLogger.info(logInput.toString());
        } catch (JsonProcessingException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }

    @AfterReturning(value = "execution(public * oauth.demoproject.app.ctrl.CustomErrController.*(..))", returning = "returnValue")
    public void outReturnErrResValue(JoinPoint jp, Object returnValue) {
        ResponseEntity<?> entity = (ResponseEntity<?>) returnValue;
        final StringBuilder logInput = getLogFileInputSb(jp.getSignature().getName(), "ResponseValue:");

        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT); // jsonを整形

        try {
            logInput.append(mapper.writeValueAsString(entity.getBody()));
            responseLogger.info(logInput.toString());
        } catch (JsonProcessingException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }

    // @AfterReturning(value = "execution(public *
    // oauth.demoproject.app.ctrl.ControllerExceptionHandler.*(..))", returning =
    // "returnValue")
    // public void outReturnErrResValue2(JoinPoint jp, Object returnValue) {
    // ResponseEntity<Object> entity = (ResponseEntity) returnValue;
    // final StringBuilder logInput = getLogFileInputSb(jp.getSignature().getName(),
    // "ResponseValue:");
    //
    // ObjectMapper mapper = new
    // ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT); // jsonを整形
    //
    // try {
    // logInput.append(mapper.writeValueAsString(entity.getBody()));
    // responseLogger.info(logInput.toString());
    // } catch (JsonProcessingException e) {
    // // TODO 自動生成された catch ブロック
    // e.printStackTrace();
    // }
    // }

}
