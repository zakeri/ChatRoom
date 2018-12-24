package ir.ideacenter.chatroom.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetRoomsResponse {

    @SerializedName("results")
    private List<Room> rooms;

    public GetRoomsResponse() {

    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
