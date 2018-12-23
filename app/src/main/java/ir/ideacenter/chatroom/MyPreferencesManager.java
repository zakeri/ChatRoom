package ir.ideacenter.chatroom;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPreferencesManager {
    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null;
    private static MyPreferencesManager instance = null;

    public static MyPreferencesManager getInstance(Context context) {
        if (instance == null) {
            instance = new MyPreferencesManager(context);
        }
        return instance;
    }

    private MyPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public String getUsername() {
        String username = sharedPreferences.getString("username", null);
        return username;
    }

    public void putUsername(String username) {
        editor.putString("username", username);
        editor.apply();
    }

    public String getAccessToken() {
        String accessToken = sharedPreferences.getString("access_token", null);
        return accessToken;
    }

    public void putAccessToken(String accessToken) {
        editor.putString("access_token", accessToken);
        editor.apply();
    }
}
