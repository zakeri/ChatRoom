package ir.ideacenter.chatroom.Models;

public class ErrorResponse {
    private String name;
    private String message;

    public ErrorResponse() {
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
