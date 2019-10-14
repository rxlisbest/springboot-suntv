package net.ruixinglong.suntv.handler;

import net.ruixinglong.suntv.bean.ExceptionBean;
import net.ruixinglong.suntv.exception.BadRequestException;
import net.ruixinglong.suntv.exception.ForbiddenException;
import net.ruixinglong.suntv.exception.NotFoundException;
import net.ruixinglong.suntv.exception.InternalServerErrorException;
import net.ruixinglong.suntv.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
@ResponseBody
public class SunTvExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity<Object> UnauthorizedExceptionHandler(HttpServletRequest request, UnauthorizedException exception) {
        ResponseEntity<Object> responseEntity = null;
        if (exception.getMessage() == null || exception.getMessage().length() == 0) {
            responseEntity = new ResponseEntity<Object>(setExceptionBean(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), request.getRequestURI()), HttpStatus.UNAUTHORIZED);
        } else {
            responseEntity = new ResponseEntity<Object>(setExceptionBean(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), exception.getMessage(), request.getRequestURI()), HttpStatus.UNAUTHORIZED);
        }
        return responseEntity;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> BadRequestExceptionHandler(HttpServletRequest request, BadRequestException exception) {
        ResponseEntity<Object> responseEntity = null;
        if (exception.getMessage() == null || exception.getMessage().length() == 0) {
            responseEntity = new ResponseEntity<Object>(setExceptionBean(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), request.getRequestURI()), HttpStatus.BAD_REQUEST);
        } else {
            responseEntity = new ResponseEntity<Object>(setExceptionBean(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), exception.getMessage(), request.getRequestURI()), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> NotFoundExceptionHandler(HttpServletRequest request, NotFoundException exception) {
        ResponseEntity<Object> responseEntity = null;
        if (exception.getMessage() == null || exception.getMessage().length() == 0) {
            responseEntity = new ResponseEntity<Object>(setExceptionBean(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), request.getRequestURI()), HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity<Object>(setExceptionBean(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage(), request.getRequestURI()), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({ForbiddenException.class})
    public ResponseEntity<Object> ForbiddenExceptionHandler(HttpServletRequest request, ForbiddenException exception) {
        ResponseEntity<Object> responseEntity = null;
        if (exception.getMessage() == null || exception.getMessage().length() == 0) {
            responseEntity = new ResponseEntity<Object>(setExceptionBean(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), request.getRequestURI()), HttpStatus.FORBIDDEN);
        } else {
            responseEntity = new ResponseEntity<Object>(setExceptionBean(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), exception.getMessage(), request.getRequestURI()), HttpStatus.FORBIDDEN);
        }
        return responseEntity;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({InternalServerErrorException.class})
    public ResponseEntity<Object> InternalServerErrorExceptionHandler(HttpServletRequest request, InternalServerErrorException exception) {
        ResponseEntity<Object> responseEntity = null;
        if (exception.getMessage() == null || exception.getMessage().length() == 0) {
            responseEntity = new ResponseEntity<Object>(setExceptionBean(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), request.getRequestURI()), HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            responseEntity = new ResponseEntity<Object>(setExceptionBean(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), exception.getMessage(), request.getRequestURI()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    private ExceptionBean setExceptionBean(int status, String error, String path) {
        ExceptionBean exceptionBean = new ExceptionBean();
        exceptionBean.setTimestamp(Long.toString(new Date().getTime() / 1000));
        exceptionBean.setStatus(status);
        exceptionBean.setError(error);
        exceptionBean.setMessage("No message available");
        exceptionBean.setPath(path);
        return exceptionBean;
    }

    private ExceptionBean setExceptionBean(int status, String error, String message, String path) {
        ExceptionBean exceptionBean = new ExceptionBean();
        exceptionBean.setTimestamp(Long.toString(new Date().getTime() / 1000));
        exceptionBean.setStatus(status);
        exceptionBean.setError(error);
        exceptionBean.setMessage(message);
        exceptionBean.setPath(path);
        return exceptionBean;
    }
}
