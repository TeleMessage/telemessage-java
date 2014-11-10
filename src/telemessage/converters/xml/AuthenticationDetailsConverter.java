package telemessage.converters.xml;

import org.apache.commons.lang3.ArrayUtils;
import org.simpleframework.xml.Element;
import telemessage.web.services.AuthenticationDetails;

public class AuthenticationDetailsConverter implements XMLConverter<AuthenticationDetailsConverter.UserFrom> {
    public UserFrom convert(Object...args) {
        if (ArrayUtils.isEmpty(args) || args.length < 1)
            return null;
        AuthenticationDetails authenticationDetails = (AuthenticationDetails)args[0];
        if (authenticationDetails != null)
            return new UserFrom(authenticationDetails);
        return null;
    }

    static class UserFrom {
        UserFrom() {
        }

        UserFrom(AuthenticationDetails authenticationDetails) {
            ciml = new CIML(new CIML.NAML(new CIML.NAML.LoginDetails(authenticationDetails.getUsername(), authenticationDetails.getPassword())));
        }

        @Element(name = "CIML") CIML ciml;
        private static class CIML {
            private CIML(NAML naml) {
                this.naml = naml;
            }

            @Element (name = "NAML") NAML naml;
            private static class NAML {

                private NAML(LoginDetails loginDetails) {
                    this.loginDetails = loginDetails;
                }

                @Element (name = "LOGIN_DETAILS") LoginDetails loginDetails;
                private static class LoginDetails {
                    private LoginDetails(String username, String password) {
                        this.username = username;
                        this.password = password;
                    }

                    @Element (name = "USER_NAME") String username;
                    @Element (name = "PASSWORD") String password;
                }
            }
        }
    }
}
