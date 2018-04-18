package com.jenisk.firebase_realtime_db;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jenisk.firebase_realtime_db.adapter.UserListAdapter;
import com.jenisk.firebase_realtime_db.model.UserDetail;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fabAddUser;
    DatabaseReference databaseReference;
    static ArrayList<UserDetail> userDetailsList;
    public static boolean isAllShowOnMap;
    UserListAdapter userListAdapter;
    private RecyclerView rvListUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        bindView();
        addListner();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_map:
                isAllShowOnMap=true;
                startActivity(new Intent(MainActivity.this, ShowOnMapActivity.class));
            break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void bindView() {
        rvListUser = (RecyclerView) findViewById(R.id.rvUserList);
        fabAddUser = (FloatingActionButton) findViewById(R.id.fabAddUser);
    }

    private void init() {
        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        userDetailsList = new ArrayList<UserDetail>();

    }

    private void addListner() {
        fabAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddUserActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                userDetailsList.clear();
                for (DataSnapshot useSnapshot : dataSnapshot.getChildren()) {
                    UserDetail userDetail = useSnapshot.getValue(UserDetail.class);
                    userDetailsList.add(userDetail);
                }
                userListAdapter = new UserListAdapter(MainActivity.this, userDetailsList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                rvListUser.setLayoutManager(mLayoutManager);
                rvListUser.setItemAnimator(new DefaultItemAnimator());
                rvListUser.setAdapter(userListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
