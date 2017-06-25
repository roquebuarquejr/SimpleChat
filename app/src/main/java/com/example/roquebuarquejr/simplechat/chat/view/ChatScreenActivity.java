package com.example.roquebuarquejr.simplechat.chat.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.roquebuarquejr.simplechat.R;
import com.example.roquebuarquejr.simplechat.chat.presenter.ChatLoginPresenterImpl;
import com.firebase.client.Firebase;

/**
 * Created by roque
 */
public class ChatScreenActivity extends AppCompatActivity {
    private ChatLoginPresenterImpl presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_service);
        Firebase.setAndroidContext(this);
        Toast.makeText(ChatScreenActivity.this, "Welcome, " + getIntent().getStringExtra("username") + "!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.chat_activity_frame_layout, new ChatFragment(), "chat");
        transaction.commit();
        presenter = new ChatLoginPresenterImpl();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_chat, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() == 0) {
            manager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.chat_activity_frame_layout, new UserListFragment(), "list").addToBackStack("list").commit();
        } else if (manager.getBackStackEntryCount() != 0) {
            manager.popBackStack();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.removeUserFromCurrentUsers(getIntent().getStringExtra("uid"));
    }
}
