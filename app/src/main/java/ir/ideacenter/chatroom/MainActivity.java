package ir.ideacenter.chatroom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hide top action bar; we want fragment pager
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        if (MyPreferencesManager.getInstance(MainActivity.this).getAccessToken() == null) {
            openRegisterFragment();
        }
        else {
            openAppFragment();
        }
    }

    private void openRegisterFragment() {
        RegisterFragment registerFragment = new RegisterFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, registerFragment)
                .addToBackStack(null)
                .commit();
    }

    private void openAppFragment() {
        RoomsFragment roomsFragment = new RoomsFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, roomsFragment)
                .commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(MainActivity.this).unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(
                broadcastReceiver,
                new IntentFilter("login_done")
        );
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            getSupportFragmentManager().popBackStack();
            openAppFragment();
        }
    };
}
