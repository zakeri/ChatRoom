package ir.ideacenter.chatroom.Models;

import com.google.gson.annotations.SerializedName;

public class LoginErrorResponse {
    public String error;

    @SerializedName("error_description")
    public String errorDescription;

    public LoginErrorResponse() {

    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
