package ir.ideacenter.chatroom.Data;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import ir.ideacenter.chatroom.Models.ErrorResponse;
import ir.ideacenter.chatroom.Models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterUserController {

    ChatRoomAPI.RegisterUserCallback registerUserCallback;

    public RegisterUserController(ChatRoomAPI.RegisterUserCallback registerUserCallback) {
        this.registerUserCallback = registerUserCallback;
    }

    public void start(User user) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ChatRoomAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ChatRoomAPI chatRoomAPI = retrofit.create(ChatRoomAPI.class);
        Call<User> call = chatRoomAPI.registerUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("TAG123", "OnResponse " + response.code());
                if (response.isSuccessful()) {
                    registerUserCallback.onResponse(true, null, response.body());
                }
                else {
                    try {
                        String errorMessageJson = response.errorBody().string();
                        Gson gson = new Gson();
                        ErrorResponse errorResponse = gson.fromJson(errorMessageJson, ErrorResponse.class);
                        registerUserCallback.onResponse(false, errorResponse.getMessage(), null);
                    } catch (Exception IOException) {
                        registerUserCallback.onResponse(false, "IOException", null);
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("TAG123", "onFailure " + t.getCause().getMessage());
                registerUserCallback.onFailure(t.getCause().getMessage());
            }
        });
    }
}
