package ir.ideacenter.chatroom;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ir.ideacenter.chatroom.Models.Room;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {
    List<Room> items;

    public RoomAdapter(List<Room> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_room, viewGroup, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder roomViewHolder, int i) {
        roomViewHolder.name.setText(items.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class RoomViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.room_name);
        }
    }
}
