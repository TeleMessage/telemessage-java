package telemessage.converters.xml;

import org.apache.commons.lang3.ArrayUtils;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import telemessage.web.services.Recipient;

public class RecipientConverter implements XMLConverter<RecipientConverter.DeviceInformation> {

    @Override
    public DeviceInformation convert(Object... args) {
        if (ArrayUtils.isEmpty(args) || args.length < 1)
            return null;
        Recipient recipient = (Recipient)args[0];
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
