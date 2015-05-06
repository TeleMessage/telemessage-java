package telemessage.web.services;


import com.mikerusoft.jsonable.annotations.JsonClass;
import com.mikerusoft.jsonable.annotations.JsonField;

/**
 * This class is like PropertyMessage,
 * but it's possible to add other functionality, something like overrideCallback and etc
 * @author Grinfeld Mikhail
 * @since Feb 25, 2010 6:18:41 PM
 */
@JsonClass
public class Property {
    @JsonField private String name;
    @JsonField private String value;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}