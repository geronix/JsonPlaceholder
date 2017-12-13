package team.max.jsonplaceholder.user.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import team.max.jsonplaceholder.user.view.adapter.UsersAdapter;
import team.max.jsonplaceholder.user.view.api.UsersView;
import team.max.tzrustam.R;
import team.max.jsonplaceholder.user.dto.UserDTO;
import team.max.jsonplaceholder.user.presentor.UserPresenter;
import team.max.jsonplaceholder.user.view.activity.UserDetailPostsActivity;

public class UsersFragment extends Fragment implements UsersView {

    public static final String KEY_USER = "user";
    public static final String KEY_SAVE_USERS = "save_users";

    public static UsersFragment newInstance(){ return new UsersFragment();}

    @BindView(R.id.recycler_view)
    protected RecyclerView recyclerView;

    @BindView(R.id.progress)
    protected ProgressBar progress;

    private UserPresenter presenter;
    private UsersAdapter adapter;

    private ArrayList<UserDTO> users;
    private boolean isShowUserDetailView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new UserPresenter(this, getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        ButterKnife.bind(this, view);
        isShowUserDetailView = false;

        initRecyclerView();

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_SAVE_USERS, users);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null){
            users = (ArrayList<UserDTO>) savedInstanceState.getSerializable(KEY_SAVE_USERS);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(users == null){
            presenter.loadUsers();
        } else if(!isShowUserDetailView){
            showUsers(users);
        }
    }

    @Override
    public void startProgress() {
        recyclerView.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hiddenProgress() {
        recyclerView.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
    }

    private void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void showUsers(List<UserDTO> users) {
        this.users = (ArrayList<UserDTO>) users;
        if(adapter == null){
            adapter = new UsersAdapter(users, new UsersAdapter.OnItemClickListener() {
                @Override
                public void onUserItemClicked(UserDTO user) {
                    Intent intent = new Intent(getActivity(), UserDetailPostsActivity.class);
                    intent.putExtra(KEY_USER, user);
                    startActivity(intent);
                    isShowUserDetailView = true;
                }
            });
            recyclerView.setAdapter(adapter);
        }
    }

}
