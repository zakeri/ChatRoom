package ir.ideacenter.chatroom.Data;

import android.util.Log;

import ir.ideacenter.chatroom.Models.GetRoomsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class  GetRoomsController {
    ChatRoomAPI.GetRoomsCallback getRoomsCallback;

    public GetRoomsController(ChatRoomAPI.GetRoomsCallback getRoomsCallback) {
        this.getRoomsCallback = getRoomsCallback;
    }

    public void start(String auth) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ChatRoomAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChatRoomAPI chatRoomAPI = retrofit.create(ChatRoomAPI.class);
        Call<GetRoomsResponse> call = chatRoomAPI.getRooms(auth);
        call.enqueue(new Callback<GetRoomsResponse>() {
            @Override
            public void onResponse(Call<GetRoomsResponse> call, Response<GetRoomsResponse> response) {
                Log.d("TAG123", "OnResponse " + response.code());
                if (response.isSuccessful()) {
                    getRoomsCallback.onResponse(response.body());
                }
                else {
                    try {
                        String getRoomsResponseJson = response.errorBody().string();
                        getRoomsCallback.onFailure(getRoomsResponseJson);
                    }
                    catch (Exception IOException) {
                        getRoomsCallback.onFailure("IOException");
                    }
                }
            }

            @Override
            public void onFailure(Call<GetRoomsResponse> call, Throwable t) {
                Log.d("TAG123", "onFailure " + t.getCause().getMessage());
                getRoomsCallback.onFailure(t.getCause().getMessage());
            }
        });
    }
}
