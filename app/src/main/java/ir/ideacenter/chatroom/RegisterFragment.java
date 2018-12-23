package ir.ideacenter.chatroom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ir.ideacenter.chatroom.Data.ChatRoomAPI;
import ir.ideacenter.chatroom.Data.LoginUserController;
import ir.ideacenter.chatroom.Data.RegisterUserController;
import ir.ideacenter.chatroom.Models.TokenResponse;
import ir.ideacenter.chatroom.Models.User;

public class RegisterFragment extends Fragment {
    EditText username;
    EditText password;
    EditText email;
    Button register;

    RegisterUserController registerUserController;
    LoginUserController loginUserController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);

        ChatRoomAPI.RegisterUserCallback registerUserCallback =
                new ChatRoomAPI.RegisterUserCallback() {
                    @Override
                    public void onResponse(boolean success, String errorMessage, User user) {
                        if (success) {
                            Toast.makeText(getActivity(), "DONE " + user.getUsername(), Toast.LENGTH_SHORT).show();
                            LoginUser();
                        }
                        else {
                            Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(String cause) {
                        Toast.makeText(getActivity(), cause, Toast.LENGTH_SHORT).show();
                    }
                };

        ChatRoomAPI.LoginUserCallback loginUserCallback =
            new ChatRoomAPI.LoginUserCallback() {
                @Override
                public void onResponse(boolean success, String errorMessage, TokenResponse tokenResponse) {
                    if (success) {
                        Toast.makeText(getActivity(), "Token " + tokenResponse.getAccessToken(), Toast.LENGTH_SHORT).show();

                        MyPreferencesManager.getInstance(getActivity()).putUsername(username.getText().toString());
                        MyPreferencesManager.getInstance(getActivity()).putAccessToken(tokenResponse.getAccessToken());

                        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(
                                new Intent("login_done")
                        );
                    }
                    else {
                        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(String cause) {
                    Toast.makeText(getActivity(), cause, Toast.LENGTH_SHORT).show();
                }
            };

        registerUserController = new RegisterUserController(registerUserCallback);
        loginUserController = new LoginUserController(loginUserCallback);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                user.setEmail(email.getText().toString());
                registerUserController.start(user);
            }
        });
    }

    private void LoginUser() {
        loginUserController.start(
                username.getText().toString(),
                password.getText().toString());
    }

    private void findViews(View view) {
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        email = view.findViewById(R.id.email);
        register = view.findViewById(R.id.register);
    }
}
