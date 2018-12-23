package ir.ideacenter.chatroom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ir.ideacenter.chatroom.Data.ChatRoomAPI;
import ir.ideacenter.chatroom.Data.RegisterUserController;
import ir.ideacenter.chatroom.Models.User;

public class RegisterFragment extends Fragment {
    EditText username;
    EditText password;
    EditText email;
    Button register;

    RegisterUserController registerUserController;

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
                    public void onResponse(User user) {
                        Toast.makeText(getActivity(), user.getUsername(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(String cause) {
                        Toast.makeText(getActivity(), cause, Toast.LENGTH_SHORT).show();
                    }
                };

        registerUserController = new RegisterUserController(registerUserCallback);

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

    private void findViews(View view) {
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        email = view.findViewById(R.id.email);
        register = view.findViewById(R.id.register);
    }
}