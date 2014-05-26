package telemessage.web.services;

import telemessage.web.rest.RestClass;
import telemessage.web.rest.RestField;

/**
 * @author Grinfeld Mikhail
 * @since Feb 25, 2010 6:32:41 PM
 */
@RestClass
public class Recipient {

    public enum Type {
        MOBILE,
        BUSINESS_PHONE,
        HOME_PHONE,
        EMAIL,
        SMS,
        FAX,
        MMS
    }

    @RestField private String value;
    /**
     * defines destination type
     */
    @RestField private String type;
    // currently supported only value 0. For future use.
    @RestField private int addresType = 0;
    @RestField private String description;

    public Recipient() {}

    public Recipient(String value, String type, String description) {
        this.value = value;
        this.type = type;
        this.description = description;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
    public Type getType() { return type != null ? Type.valueOf(type) : null; }
    public void setType(Type type) { this.type = type.name(); }
}