package com.example.swipetodelete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

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
    private RecyclerTouchListener touchListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new MyAdapter(getApplicationContext(), messages);
        itemDecor= new DividerItemDecoration(this, VERTICAL);
        recyclerView = findViewById(R.id.recyclerView);
        setUpRecyclerView();

        setMessages();


        touchListener = new RecyclerTouchListener(this,recyclerView);
        touchListener

                .setSwipeOptionViews(R.id.delete)
                .setSwipeable(R.id.rowFG, R.id.rowBG, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {
                        switch (viewID){
                            case R.id.delete:
                                DialogShow(position);
                                break;
                        }
                    }
                });
    }

    private void setUpRecyclerView(){
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(itemDecor);

    }

    private void setMessages() {
      message = new Message("Were na you");
      messages.add(message);

      message = new Message("Naa nako");
      messages.add(message);


    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.addOnItemTouchListener(touchListener);
    }

    public void DialogShow(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Delete")
                .setMessage("Are you sure, you want to delete ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAdapter.deleteItem(position);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        //Creating dialog box
        AlertDialog dialog  = builder.create();
        dialog.show();
    }
    }

