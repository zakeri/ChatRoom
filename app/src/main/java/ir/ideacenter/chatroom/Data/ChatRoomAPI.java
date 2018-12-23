package ir.ideacenter.chatroom.Data;

import ir.ideacenter.chatroom.Models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ChatRoomAPI {
    String BASE_URL = "https://api.backtory.com/";

    @Headers("X-Backtory-Authentication-Id:5a1d4b3de4b0afa16474fabd")
    @POST("auth/users")
    Call<User> registerUser(@Body User user);

    interface RegisterUserCallback {
        void onResponse(boolean success, String errorMessage, User user);
        void onFailure(String cause);
    }

}
