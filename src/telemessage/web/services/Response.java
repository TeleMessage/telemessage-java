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

    /**
     * returns TeleMessage response code
     * @return TeleMessage response code
     */
    public int getResultCode() { return resultCode; }
    public void setResultCode(int resultCode) { this.resultCode = resultCode; }

    /**
     *
     * @return TeleMessage textual response
     */
    public String getResultDescription() { return resultDescription; }
    public void setResultDescription(String resultDescription) { this.resultDescription = resultDescription; }
}