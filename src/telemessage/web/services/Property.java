package telemessage.web.services;

import telemessage.web.rest.RestClass;
import telemessage.web.rest.RestField;

/**
 * This class is like PropertyMessage,
 * but it's possible to add other functionality, something like overrideCallback and etc
 * @author Grinfeld Mikhail
 * @since Feb 25, 2010 6:18:41 PM
 */
@RestClass
public class Property {
    @RestField private String name;
    @RestField private String value;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}