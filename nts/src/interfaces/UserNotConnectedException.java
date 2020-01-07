package interfaces;

public class UserNotConnectedException extends Exception {
    private static final long serialVersionUID = 1L;

    public UserNotConnectedException(String message, Throwable err) {
        super(message, err);
    }

    public UserNotConnectedException(String message) {
        super(message);
    }

    public UserNotConnectedException(Throwable err) {
        super(err);
    }
}