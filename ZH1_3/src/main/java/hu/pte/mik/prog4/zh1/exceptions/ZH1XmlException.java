package hu.pte.mik.prog4.zh1.exceptions;

public class ZH1XmlException extends RuntimeException{
    public ZH1XmlException() {
    }

    public ZH1XmlException(String message) {
        super(message);
    }

    public ZH1XmlException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZH1XmlException(Throwable cause) {
        super(cause);
    }

    public ZH1XmlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
