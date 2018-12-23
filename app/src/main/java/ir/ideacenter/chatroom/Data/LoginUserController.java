package ir.ideacenter.chatroom.Data;

import android.util.Log;

import com.google.gson.Gson;

import ir.ideacenter.chatroom.Models.LoginErrorResponse;
import ir.ideacenter.chatroom.Models.RegisterErrorResponse;
import ir.ideacenter.chatroom.Models.TokenResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginUserController {
    ChatRoomAPI.LoginUserCallback loginUserCallback;

    public LoginUserController(ChatRoomAPI.LoginUserCallback loginUserCallback) {
        this.loginUserCallback = loginUserCallback;
    }

    public void start(String username, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ChatRoomAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ChatRoomAPI chatRoomAPI = retrofit.create(ChatRoomAPI.class);
        Call<TokenResponse> call = chatRoomAPI.loginUser(username, password);
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                Log.d("TAG123", "OnResponse " + response.code());
                if (response.isSuccessful()) {
                    loginUserCallback.onResponse(true, null, response.body());
                }
                else {
                    try {
                        String loginErrorResponseJson = response.errorBody().string();
                        Gson gson = new Gson();
                        LoginErrorResponse loginErrorResponse = gson.fromJson(loginErrorResponseJson, LoginErrorResponse.class);
                        loginUserCallback.onResponse(false, loginErrorResponse.getErrorDescription(), null);
                    } catch (Exception IOException) {
                        loginUserCallback.onResponse(false, "IOException", null);
                    }
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Log.d("TAG123", "onFailure " + t.getCause().getMessage());
                loginUserCallback.onFailure(t.getCause().getMessage());
            }
        });
    }
}
