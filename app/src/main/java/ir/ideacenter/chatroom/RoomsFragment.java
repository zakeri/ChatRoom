package ir.ideacenter.chatroom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import ir.ideacenter.chatroom.Data.ChatRoomAPI;
import ir.ideacenter.chatroom.Data.GetRoomsController;
import ir.ideacenter.chatroom.Models.GetRoomsResponse;
import ir.ideacenter.chatroom.Models.Room;

public class RoomsFragment extends Fragment {
    RecyclerView roomList;
    ProgressBar progressBar;

    GetRoomsController getRoomsController;
    List<Room> roomItems = new ArrayList<>();
    RoomAdapter roomAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rooms, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        initRoomList();

        progressBar.setVisibility(View.VISIBLE);

        getRoomsController = new GetRoomsController(getRoomsCallback);
        getRoomsController.start(
                "bearer " + MyPreferencesManager.getInstance(getActivity()).getAccessToken());
    }

    ChatRoomAPI.GetRoomsCallback getRoomsCallback = new ChatRoomAPI.GetRoomsCallback() {
        @Override
        public void onResponse(GetRoomsResponse getRoomsResponse) {

            if (progressBar.isShown()) {
                progressBar.setVisibility(View.INVISIBLE);
            }

            roomItems.clear();
            roomItems.addAll(getRoomsResponse.getRooms());
            roomAdapter.notifyDataSetChanged();
        }

        @Override
        public void onFailure(String cause) {
            roomItems.clear();
            roomList.notifyAll();
        }
    };

    private void initRoomList() {
        roomAdapter = new RoomAdapter(roomItems);
        roomList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        roomList.setAdapter(roomAdapter);
    }

    private void findViews(View view) {
        roomList = view.findViewById(R.id.rooms_list);
        progressBar = view.findViewById(R.id.progress_bar);
    }
}
