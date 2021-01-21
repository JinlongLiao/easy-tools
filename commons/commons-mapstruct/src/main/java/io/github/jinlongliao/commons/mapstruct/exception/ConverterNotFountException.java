package io.github.jinlongliao.commons.mapstruct.exception;
/**
 * 字段值转换类找不到
 * @author liaojinlong
 * @since 2020/11/23 21:58
*/

public class ConverterNotFountException extends RuntimeException{
    public ConverterNotFountException() {
        super();
    }

    public ConverterNotFountException(String message) {
        super(message);
    }

    public ConverterNotFountException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConverterNotFountException(Throwable cause) {
        super(cause);
    }

    protected ConverterNotFountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
