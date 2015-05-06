package telemessage.web.services;


import com.mikerusoft.jsonable.annotations.JsonClass;
import com.mikerusoft.jsonable.annotations.JsonField;

/**
 * @author Grinfeld Mikhail
 * @since Feb 25, 2010 6:32:41 PM
 */
@JsonClass
public class Recipient {

    public enum Type {
        MOBILE (10),
        BUSINESS_PHONE (20),
        HOME_PHONE (30),
        EMAIL (40),
        SMS (60),
        FAX (80),
        MMS (110);

        int id;

        Type(int id) {
            this.id = id;
        }

        public static String name(int id) {
            for (Type d : Type.values()) {
                if (id == d.id)
                    return d.name();
            }
            return null;
        }
    }

    @JsonField private String value;
    /**
     * defines destination type
     */
    @JsonField private String type;
    // currently supported only value 0. For future use.
    @JsonField private int addresType = 0;
    @JsonField private String description;

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