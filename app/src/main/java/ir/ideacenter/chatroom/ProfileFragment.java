package ir.ideacenter.chatroom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ProfileFragment extends android.support.v4.app.Fragment {

    TextView username;
    Button logout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        username.setText(MyPreferencesManager.getInstance(getActivity()).getUsername());
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPreferencesManager.getInstance(getActivity()).clearEveryThing();
                getActivity().finish();
            }
        });
    }

    private void findViews(View view) {
        username = view.findViewById(R.id.profile_username);
        logout = view.findViewById(R.id.profile_logout);
    }
}
