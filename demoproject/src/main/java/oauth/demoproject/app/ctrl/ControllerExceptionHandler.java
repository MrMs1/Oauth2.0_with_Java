package oauth.demoproject.app.ctrl;

import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    // DTOチェックでエラー
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ResponseErrorBody.validErrResBuild(ex.getMessage()), headers, status,
                request);
    }

    // パスパラメータチェックでエラー
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        return handleExceptionInternal(ex,
                ResponseErrorBody.validErrResBuild(getResultMessage(ex.getConstraintViolations().iterator())), null,
                HttpStatus.BAD_REQUEST,
                request);
    }

    protected String getResultMessage(final Iterator<ConstraintViolation<?>> violationIterator) {
        final StringBuilder resultMessageBuilder = new StringBuilder();
        while (violationIterator.hasNext() == true) {

            final ConstraintViolation<?> constraintViolation = violationIterator.next();
            resultMessageBuilder.append(constraintViolation.getMessage()); // バリデーションチェック失敗時のメッセージ

            if (violationIterator.hasNext() == true) {
                resultMessageBuilder.append(", ");
            }
        }
        return resultMessageBuilder.toString();
    }

    // 400のException
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ResponseErrorBody.badReqErrResBuild(ex.getMessage()), null,
                HttpStatus.BAD_REQUEST,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(
            ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ResponseErrorBody.badReqErrResBuild(ex.getMessage()), headers, status,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ResponseErrorBody.badReqErrResBuild(ex.getMessage()), headers, status,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ResponseErrorBody.badReqErrResBuild(ex.getMessage()), headers, status,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(
            MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ResponseErrorBody.badReqErrResBuild(ex.getMessage()), headers, status,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(
            BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ResponseErrorBody.badReqErrResBuild(ex.getMessage()), headers, status,
                request);
    }

    // 500のException
    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(
            MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ResponseErrorBody.intrnlServErrResBuild(ex.getMessage()), headers, status,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(
            ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ResponseErrorBody.intrnlServErrResBuild(ex.getMessage()), headers, status,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(
            HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ResponseErrorBody.intrnlServErrResBuild(ex.getMessage()), headers, status,
                request);
    }

    // 503のException
    @Override
    @Nullable
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(
            AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {

        if (webRequest instanceof ServletWebRequest) {
            ServletWebRequest servletWebRequest = (ServletWebRequest) webRequest;
            HttpServletResponse response = servletWebRequest.getResponse();
            if (response != null && response.isCommitted()) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Async request timed out");
                }
                return null;
            }
        }

        return handleExceptionInternal(ex, ResponseErrorBody.serviceUnavErrResBuild(ex.getMessage()), headers, status,
                webRequest);
    }

    // 上記以外のException
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        return super.handleExceptionInternal(ex, ResponseErrorBody.intrnlServErrResBuild(ex.getMessage()), null,
                HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }

}
