package io.github.jinlongliao.commons.mapstruct.exception;

/**
 * 字段值转换类异常
 *
 * @author liaojinlong
 * @since 2020/11/23 21:58
 */

public class ConverterException extends RuntimeException {
    public ConverterException() {
        super();
    }

    public ConverterException(String message) {
        super(message);
    }

    public ConverterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConverterException(Throwable cause) {
        super(cause);
    }

    protected ConverterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
