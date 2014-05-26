package telemessage.converters.xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import telemessage.web.services.Recipient;

public class RecipientConverter implements XMLConverter<RecipientConverter.DeviceInformation, Recipient> {

    @Override
    public DeviceInformation convert(Recipient recipient, Object... args) {
        if (recipient != null) {
            return new DeviceInformation(recipient.getType().name(), recipient.getValue(), recipient.getDescription());
        }
        return null;
    }

    @Root(name = "DEVICE_INFORMATION")
    static class DeviceInformation {
        DeviceInformation(String type, String value, String description) {
            this.type = new DeviceType(type);
            this.value = value;
            this.description = description;
        }

        @Element (name = "DEVICE_TYPE") DeviceType type;
        @Element (name = "DEVICE_VALUE") String value;
        @Element (name = "DEVICE_DESCRIPTION", required = false) String description;


        static class DeviceType {
            DeviceType() {
            }

            DeviceType(String dType) {
                this.type = dType;
            }

            @Attribute (name = "DEVICE_TYPE") String type;
        }
    }

}
