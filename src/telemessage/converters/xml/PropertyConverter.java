package telemessage.converters.xml;

import org.apache.commons.lang3.ArrayUtils;
import org.simpleframework.xml.Element;
import telemessage.web.services.Property;

public class PropertyConverter implements XMLConverter<PropertyConverter.PropertyMessage> {

    @Override
    /**
     * Expects first element to be integer
     */
    public PropertyMessage convert(Object...args) {
        if (ArrayUtils.isEmpty(args) || args.length < 2)
            return null;
        Property property = (Property)args[0];
        if (property != null)
            return new PropertyMessage((Integer)args[1], property.getName(), property.getValue());
        return null;
    }


    static class PropertyMessage {

        PropertyMessage(int messageIndex, String propertyName, String propertyValue) {
            this.messageIndex = messageIndex;
            this.propertyName = propertyName;
            this.propertyValue = propertyValue;
        }

        @Element(name = "MESSAGE_INDEX") int messageIndex;
        @Element(name = "PROPERTY_NAME") String propertyName;
        @Element(name = "PROPERTY_VALUE") String propertyValue;
    }
}
