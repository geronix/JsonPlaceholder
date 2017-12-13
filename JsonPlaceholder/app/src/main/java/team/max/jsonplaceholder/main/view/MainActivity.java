package team.max.jsonplaceholder.main.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import team.max.jsonplaceholder.user.view.fragment.UsersFragment;
import team.max.tzrustam.R;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        if(savedInstanceState == null) {
            UsersFragment fragment = UsersFragment.newInstance();
            fragmentManager.beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }
    }

}
