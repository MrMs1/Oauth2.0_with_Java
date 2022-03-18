package oauth.demoproject.app.ctrl;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@RestController
@RequestMapping("/error")
public class CustomErrController extends AbstractErrorController {

    private static final String BODY_TITLE = "title";
    private static final String BODY_MESSAGE = "message";
    private static final String BODY_EXCEPTION = "exception";
    private static final String BODY_TRACE = "trace";

    public CustomErrController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping
    public ResponseEntity<Map<String, Object>> error(final HttpServletRequest request) {
        ServletWebRequest serbletWebReq = new ServletWebRequest(request);
        DefaultErrorAttributes defaultErrAtr = new DefaultErrorAttributes();
        ErrorAttributeOptions options = ErrorAttributeOptions.of(
                ErrorAttributeOptions.Include.BINDING_ERRORS,
                ErrorAttributeOptions.Include.EXCEPTION,
                ErrorAttributeOptions.Include.MESSAGE,
                ErrorAttributeOptions.Include.STACK_TRACE);

        final Map<String, Object> defaultBody = defaultErrAtr.getErrorAttributes(serbletWebReq, options);
        final HttpStatus status = this.getStatus(request);

        return customResponse(defaultBody, status);
    }

    private ResponseEntity<Map<String, Object>> customResponse(Map<String, Object> defaultBody, HttpStatus status) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(BODY_TITLE, defaultBody.get("error"));
        body.put(BODY_MESSAGE, defaultBody.get(BODY_MESSAGE));
        body.put(BODY_EXCEPTION, defaultBody.get(BODY_EXCEPTION));
        body.put(BODY_TRACE, defaultBody.get(BODY_TRACE));

        String exceptionName = defaultBody.get(BODY_EXCEPTION).toString();

        // バリデーションチェックエラー
        if (ConstraintViolationException.class.getName().equals(exceptionName)
                || BindException.class.getName().equals(exceptionName)
                || MethodArgumentNotValidException.class.getName().equals(exceptionName)) {
            body.replace(BODY_TITLE, "Validation Error");
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(body, status);
        }
        // 400
        if (MissingServletRequestParameterException.class.getName().equals(exceptionName)
                || ServletRequestBindingException.class.getName().equals(exceptionName)
                || TypeMismatchException.class.getName().equals(exceptionName)
                || HttpMessageNotReadableException.class.getName().equals(exceptionName)
                || MissingServletRequestPartException.class.getName().equals(exceptionName)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(body, status);
        }
        // 503
        if (AsyncRequestTimeoutException.class.getName().equals(exceptionName)) {
            status = HttpStatus.SERVICE_UNAVAILABLE;
            return new ResponseEntity<>(body, status);
        }

        // 上記以外の例外 500
        if (exceptionName.contains(Exception.class.getName())) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(body, status);
        }
        return new ResponseEntity<>(defaultBody, status);
    }

}
