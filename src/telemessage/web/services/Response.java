package telemessage.web.services;

import telemessage.web.rest.RestClass;
import telemessage.web.rest.RestField;

@RestClass
public class Response {

    @RestField private int resultCode;
    @RestField private  String resultDescription;

    public Response(){}
    public Response(int resultCode, String resultDescription) {
        this.resultCode = resultCode;
        this.resultDescription = resultDescription;
    }

    public int getResultCode() { return resultCode; }
    public void setResultCode(int resultCode) { this.resultCode = resultCode; }
    public String getResultDescription() { return resultDescription; }
    public void setResultDescription(String resultDescription) { this.resultDescription = resultDescription; }
}