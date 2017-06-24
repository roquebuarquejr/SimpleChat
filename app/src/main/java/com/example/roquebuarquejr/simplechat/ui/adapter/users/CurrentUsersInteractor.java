package com.example.roquebuarquejr.simplechat.ui.adapter.users;

import com.example.roquebuarquejr.simplechat.model.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Filip on 25/02/2016.
 */
public class CurrentUsersInteractor {
    private final CurrentUsersPresenter presenter;
    private final Firebase currentUsersRef = new Firebase("https://simple-chat-6d9bd.firebaseio.com/currentUsers");

    public CurrentUsersInteractor(CurrentUsersPresenter pre) {
        this.presenter = pre;
    }

    public void request() {
        currentUsersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<User> mListOfUsers = new ArrayList<>();
                for (DataSnapshot x : dataSnapshot.getChildren()) {
                    mListOfUsers.add(x.getValue(User.class));
                }
                presenter.getChildren(mListOfUsers);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }
}
