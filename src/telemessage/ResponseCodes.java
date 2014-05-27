package telemessage;

/**
 * Enum defines list of TeleMessage error and its code
 */
public enum ResponseCodes {
    /**
     * Message accepted
     */
    MSG_ACCEPTED(100), // MSG_ACCEPTED
    /**
     * Authentication failed - user does not exist
     */
    USER_DOESNT_EXIST(300), // USER_DOESNT_EXIST
    /**
     * Authentication failed - invalid password
     */
    INVALID_PASSWORD(301), // INVALID_PASSWORD
    /**
     * Invalid destination - country not supported
     */
    COUNTRY_NOT_SUPPORTED(302), // COUNTRY_NOT_SUPPORTED
    /**
     * Invalid destination - recipient limit is exceeded
     */
    MESSAGE_DEVICE_LIMIT_EXCEEDED(303), // MESSAGE_DEVICE_LIMIT_EXCEEDED
    /**
     * Invalid destination - message limit is exceeded
     */
    MESSAGE_LIMIT_REACHED(304), // MESSAGE_LIMIT_REACHED
    /**
     * Failed to send - not enough credits
     */
    NOT_ENOUGH_CREDIT(305), // NOT_ENOUGH_CREDIT
    /**
     * Sending failed 0 quota exceeded
     */
    QUOTA_LIMIT_REACHED(306), // QUOTA_LIMIT_REACHED
    /**
     * Authentication failed - user canceled
     */
    USER_CANCELED(308), // USER_CANCELED
    /**
     * Authentication failed - user suspended
     */
    USER_SUSPENDED(309), // USER_SUSPENDED
    /**
     * Message does not contain valid destinations
     */
    MESSAGE_MUST_CONTAIN_AT_LEAST_ONE_DESTINATION(310), // MESSAGE_MUST_CONTAIN_AT_LEAST_ONE_DESTINATION
    /**
     * General error
     */
    GENERAL_ERROR(400), // GENERAL_ERROR
    /**
     * Authentication error - REST specific error for sending file messages to TeleMessage app
     */
    FAILED_MISSING_IP_DEVICE_AUTHENTICATION_DETAILS(46),
    /**
     * Unsupported Mime type error - REST specific error for sending file messages to TeleMessage app
     */
    FAILED_UNSUPPORTED_MIME_TYPE(48),
    /**
     * Attachment maximum size violation error - REST specific error for sending file messages to TeleMessage app
     */
    FAILED_MAX_FILE_SIZE_VIOLATION (49),


    ;

    /**
     * Match code returned by TeleMessage during sending messages and receiving status
     */
    public int code;

    ResponseCodes(int code) {
        this.code = code;
    }
}
