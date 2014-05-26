package telemessage.web.services;

import telemessage.web.rest.RestClass;
import telemessage.web.rest.RestField;

/**
 * @author Grinfeld Mikhail
 * @since Feb 28, 2010 5:54:25 PM
 */
@RestClass
public class Schedule {
    @RestField private long sendAt;
    @RestField private long expiredAt;

    public Schedule() {
    }

    public Schedule(long sendAt, long expiredAt) {
        this.sendAt = sendAt;
        this.expiredAt = expiredAt;
    }

    public long getSendAt() { return sendAt; }
    public void setSendAt(long sendAt) { this.sendAt = sendAt; }
    public long getExpiredAt() { return expiredAt; }
    public void setExpiredAt(long expiredAt) { this.expiredAt = expiredAt; }
}