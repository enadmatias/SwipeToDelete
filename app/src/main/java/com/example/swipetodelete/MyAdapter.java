package com.example.swipetodelete;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    List<Message> messageList;
    private Message mRecentlyDeletedItem;
    private int mRecentlyDeletedItemPosition;

    public MyAdapter(Context ctx, List<Message> list){
        this.context = ctx;
        this.messageList = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         holder.tv.setText(messageList.get(position).getMessages());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public void deleteItem(int position){
        mRecentlyDeletedItem = messageList.get(position);
        mRecentlyDeletedItemPosition = position;
        messageList.remove(position);
        notifyItemRemoved(position);

    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.textView);
        }
    }
}
