package ir.ideacenter.chatroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ir.ideacenter.chatroom.Data.ChatRoomAPI;
import ir.ideacenter.chatroom.Data.RegisterUserController;
import ir.ideacenter.chatroom.Models.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RegisterFragment registerFragment = new RegisterFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_register, registerFragment)
                .addToBackStack(null)
                .commit();
    }
}
