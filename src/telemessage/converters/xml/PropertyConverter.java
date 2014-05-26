package telemessage.converters.xml;

import org.apache.commons.lang3.ArrayUtils;
import org.simpleframework.xml.Element;
import telemessage.web.services.Property;

public class PropertyConverter implements XMLConverter<PropertyConverter.PropertyMessage, Property> {

    @Override
    /**
     * Expects first element to be integer
     */
    public PropertyMessage convert(Property property, Object...args) {
        if (property != null && !ArrayUtils.isEmpty(args))
            return new PropertyMessage((Integer)args[0], property.getName(), property.getValue());
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
