package telemessage;

/**
 * Basic Result defines success or failure of convert operation
 */
public class Result {

    boolean success;

    public Result(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() { return success; }
}
