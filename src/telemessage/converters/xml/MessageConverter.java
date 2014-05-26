package telemessage.converters.xml;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import telemessage.web.services.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageConverter implements XMLConverter<MessageConverter.TeleMessage, telemessage.web.xml.TeleMessage> {

    @Override
    public TeleMessage convert(telemessage.web.xml.TeleMessage tm, Object... args) {
        if (tm != null) {
            return new TeleMessage(new TeleMessage.TelemessageContent(tm.getMessage(), tm.getAuthenticationDetails()));
        }
        return null;
    }

    @Root (name = "TELEMESSAGE")
    public static class TeleMessage {

        TeleMessage(TelemessageContent content) {
            this.content = content;
        }

        @Element (name = "VERSION") String version = "1.5";
        @Element (name = "TELEMESSAGE_CONTENT") TelemessageContent content;


        private static class TelemessageContent {
            @Element (name = "MESSAGE") MESSAGE message;

            TelemessageContent() {
            }

            private TelemessageContent(Message message, AuthenticationDetails auth) {
                this.message = new MESSAGE(message, auth);
            }

            private static class MESSAGE {
                @Element (name = "MESSAGE_INFORMATION", required = false) MessageInformation messageInformation;
                @Element (name = "USER_FROM") AuthenticationDetailsConverter.UserFrom userFrom;
                @Element (name = "MESSAGE_CONTENT", required = false) MessageContent messageContent;
                @Element (name = "USER_TO") UserTo userTo;

                MESSAGE() {
                }

                MESSAGE(Message message, AuthenticationDetails auth) {
                    messageInformation = new MessageInformation(message.getSubject(), message.getSchedule());
                    userTo = new UserTo(new UserTo.CIML(message.getRecipients()));
                    messageContent = new MessageContent(message.getTextMessage(), message.getFileMessages(), message.getProperties());
                    userFrom = new AuthenticationDetailsConverter().convert(auth);
                }

                private static class MessageInformation {
                    MessageInformation() {
                    }

                    MessageInformation(String subject, Schedule schedule) {
                        this.subject = subject;
                        if (schedule != null) {
                            if (schedule.getSendAt() > 0 ) {
                                this.scheduled = new SimpleDateFormat("yyyymmdd hh:mm:ss").format(new Date(schedule.getSendAt()));
                            }
                            if (schedule.getExpiredAt() > 0 ) {
                                this.expiration = new SimpleDateFormat("yyyymmdd hh:mm:ss").format(new Date(schedule.getExpiredAt()));
                            }
                        }
                    }

                    @Element (name = "SUBJECT", required = false) String subject;
                    @Element (name = "SCHEDULE_TO", required = false) String scheduled;
                    @Element (name = "TTD", required = false) String expiration;
                }



                private static class MessageContent {

                    @Element (name = "TEXT_MESSAGE", required = false) TextMessage textMessage;
                    @ElementList (name = "FILE_MESSAGE", required = false, inline = true) List<FileMessageConverter.FILE_MESSAGE> fileMessages;
                    @ElementList (name = "PROPERTY_MESSAGE", required = false, inline = true) List<PropertyConverter.PropertyMessage> propertyMessages;

                    MessageContent() {
                    }

                    MessageContent(String textMessage, FileMessage[] fileMessages, Property[] properties) {
                        int i=0;
                        if (!StringUtils.isEmpty(textMessage)) {
                            this.textMessage = new TextMessage(i, textMessage);
                            i++;
                        }
                        if (!ArrayUtils.isEmpty(fileMessages)) {
                            this.fileMessages = new ArrayList<FileMessageConverter.FILE_MESSAGE>();
                            for (FileMessage f : fileMessages) {
                                this.fileMessages.add(new FileMessageConverter().convert(f, i));
                                i++;
                            }
                        }

                        if (!ArrayUtils.isEmpty(properties)) {
                            this.propertyMessages = new ArrayList<PropertyConverter.PropertyMessage>();
                            for (Property p : properties) {
                                this.propertyMessages.add(new PropertyConverter().convert(p, i));
                                i++;
                            }
                        }
                    }

                    private static class TextMessage {

                        TextMessage(int messageIndex, String text) {
                            this.messageIndex = messageIndex;
                            this.text = text;
                        }

                        @Element (name = "MESSAGE_INDEX") int messageIndex;
                        @Element (name = "TEXT") String text;
                    }
                }


                private static class UserTo {
                    @Element (name = "CIML") CIML ciml;

                    UserTo(CIML ciml) {
                        this.ciml = ciml;
                    }

                    private static class CIML {
                        @ElementList (name = "DEVICE_INFORMATION", inline = true) List<RecipientConverter.DeviceInformation> devices;

                        CIML() {}

                        CIML(Recipient[] recipients) {
                            if (!ArrayUtils.isEmpty(recipients)) {
                                devices = new ArrayList<RecipientConverter.DeviceInformation>();
                                for (Recipient r : recipients) {
                                    devices.add(new RecipientConverter().convert(r));
                                }
                            }
                        }


                    }
                }
            }

        }
    }
}
