package mx.com.nacho.utils;

/**
 * Wrapper Exception used in the EncryptUtil class.
 * @author iasandoval
 * @version 1.0
 */
public class EncryptUtilException extends Exception {

    public EncryptUtilException() {
        super();
    }

    public EncryptUtilException(String message) {
        super(message);
    }

    public EncryptUtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public EncryptUtilException(Throwable cause) {
        super(cause);
    }

    protected EncryptUtilException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
