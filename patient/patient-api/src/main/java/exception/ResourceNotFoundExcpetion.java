package exception;

/**
 * [Class Description Here]
 *
 * @author Tyler Graham
 */
public class ResourceNotFoundExcpetion extends Exception {

    public ResourceNotFoundExcpetion() {
        super();
    }

    public ResourceNotFoundExcpetion(String message) {
        super(message);
    }

    public ResourceNotFoundExcpetion(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundExcpetion(Throwable cause) {
        super(cause);
    }

    protected ResourceNotFoundExcpetion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
