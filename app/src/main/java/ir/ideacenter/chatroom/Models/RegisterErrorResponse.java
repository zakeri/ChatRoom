package ir.ideacenter.chatroom.Models;

public class RegisterErrorResponse {
    private String name;
    private String message;

    public RegisterErrorResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
