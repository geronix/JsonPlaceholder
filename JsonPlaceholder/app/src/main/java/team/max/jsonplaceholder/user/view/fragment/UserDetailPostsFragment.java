package team.max.jsonplaceholder.user.view.fragment;


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
import team.max.jsonplaceholder.user.presentor.UserDetailPostsPresenter;
import team.max.jsonplaceholder.user.view.adapter.UserDetailPostAdapter;
import team.max.tzrustam.R;
import team.max.jsonplaceholder.user.dto.PostDTO;
import team.max.jsonplaceholder.user.dto.UserDTO;
import team.max.jsonplaceholder.user.view.api.UserDetailPostsView;

public class UserDetailPostsFragment extends Fragment implements UserDetailPostsView{

    public static final String KEY_USER_DETAIL = "user_detail";
    public static final String KEY_SAVE_USER = "save_user";
    public static final String KEY_SAVE_USER_POSTS = "save_user_posts";

    public static UserDetailPostsFragment newInstance(UserDTO user){
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_USER_DETAIL, user);
        UserDetailPostsFragment fragment = new UserDetailPostsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.user_detail_posts)
    protected RecyclerView recyclerView;

    @BindView(R.id.user_detail_progress)
    protected ProgressBar progress;

    private UserDetailPostsPresenter presenter;

    private UserDTO user;
    private ArrayList<PostDTO> posts;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = (UserDTO) getArguments().getSerializable(KEY_USER_DETAIL);
        presenter = new UserDetailPostsPresenter(this, getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_detail_posts, container, false);
        ButterKnife.bind(this, view);

        initRecyclerView();

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_SAVE_USER, user);
        outState.putSerializable(KEY_SAVE_USER_POSTS, posts);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null){
            user = (UserDTO) savedInstanceState.getSerializable(KEY_SAVE_USER);
            posts = (ArrayList<PostDTO>) savedInstanceState.getSerializable(KEY_SAVE_USER_POSTS);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(posts == null){
            presenter.loadUserPosts(user.getId());
        } else {
            showUserDetailPosts(posts);
        }
    }

    @Override
    public void startProgress() {
        recyclerView.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hiddenProgress() {
        progress.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserDetailPosts(List<PostDTO> posts) {
        this.posts = (ArrayList<PostDTO>) posts;
        UserDetailPostAdapter adapter = new UserDetailPostAdapter(user, posts);
        recyclerView.setAdapter(adapter);
    }

    private void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }
}
