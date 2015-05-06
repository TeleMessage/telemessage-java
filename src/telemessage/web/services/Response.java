package telemessage.web.services;


import com.mikerusoft.jsonable.annotations.JsonClass;
import com.mikerusoft.jsonable.annotations.JsonField;

@JsonClass
public class Response {

    @JsonField private int resultCode;
    @JsonField private  String resultDescription;

    public Response(){}
    public Response(int resultCode, String resultDescription) {
        this.resultCode = resultCode;
        this.resultDescription = resultDescription;
    }

    protected void init(int resultCode, String resultDescription) {
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