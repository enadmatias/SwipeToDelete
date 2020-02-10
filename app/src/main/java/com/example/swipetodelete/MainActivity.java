package com.example.swipetodelete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL;
import static androidx.recyclerview.widget.DividerItemDecoration.VERTICAL;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Message> messages = new ArrayList<>();
    private  MyAdapter mAdapter;
    private   Message message;
    private DividerItemDecoration itemDecor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new MyAdapter(getApplicationContext(), messages);
        itemDecor= new DividerItemDecoration(this, VERTICAL);
        recyclerView = findViewById(R.id.recyclerView);
        setUpRecyclerView();

        setMessages();
    }

    private void setUpRecyclerView(){
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(itemDecor);
        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new SwipeToDeleteCallback(mAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void setMessages() {
      message = new Message("Were na you");
      messages.add(message);

      message = new Message("Naa nako");
      messages.add(message);


    }

}
