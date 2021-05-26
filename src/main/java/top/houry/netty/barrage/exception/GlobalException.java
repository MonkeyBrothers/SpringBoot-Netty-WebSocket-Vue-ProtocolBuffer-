package top.houry.netty.barrage.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.InvalidMimeTypeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
@Slf4j
public class GlobalException {

    /**
     * 处理参数不合法异常
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public void barrageIllegalArgumentException(IllegalArgumentException e) {
        log.error("[GlobalException]-[barrageIllegalArgumentException]", e);
    }

    /**
     * 处理其IO异常
     */
    @ExceptionHandler(value = IOException.class)
    public void barrageIOException(IOException e) {
        log.error("[GlobalException]-[barrageIOException]", e);
    }


    @ExceptionHandler(value = InvalidMimeTypeException.class)
    public void barrageInvalidMimeTypeException(InvalidMimeTypeException e) {
        log.error("[GlobalException]-[barrageInvalidMimeTypeException]", e);
    }

}
