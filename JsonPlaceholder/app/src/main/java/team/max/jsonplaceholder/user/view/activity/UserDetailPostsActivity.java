package team.max.jsonplaceholder.user.view.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import team.max.jsonplaceholder.user.view.fragment.UserDetailPostsFragment;
import team.max.jsonplaceholder.user.view.fragment.UsersFragment;
import team.max.tzrustam.R;
import team.max.jsonplaceholder.user.dto.UserDTO;

public class UserDetailPostsActivity extends AppCompatActivity{

    private FragmentManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail_posts);

        getSupportActionBar().setTitle("UserDetail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        UserDTO user = (UserDTO) intent.getSerializableExtra(UsersFragment.KEY_USER);

        manager = getSupportFragmentManager();
        if(savedInstanceState == null){
            UserDetailPostsFragment fragment = UserDetailPostsFragment.newInstance(user);
            manager.beginTransaction()
                    .add(R.id.user_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            super.onBackPressed();
        }
        return false;
    }
}
