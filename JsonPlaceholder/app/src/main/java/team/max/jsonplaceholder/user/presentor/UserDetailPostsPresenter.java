package team.max.jsonplaceholder.user.presentor;


import android.content.Context;

import java.util.List;

import team.max.jsonplaceholder.user.dto.PostDTO;
import team.max.jsonplaceholder.user.repository.UserRepositoryImpl;
import team.max.jsonplaceholder.user.repository.api.UserRepository;
import team.max.jsonplaceholder.user.view.api.UserDetailPostsView;

public class UserDetailPostsPresenter implements UserRepository.OnUserDetailPostsListener{

    private UserDetailPostsView view;
    private UserRepository repository;

    public UserDetailPostsPresenter(UserDetailPostsView view, Context context) {
        this.view = view;
        this.repository = new UserRepositoryImpl(context);
    }

    @Override
    public void onLoadUserPostsFinished(List<PostDTO> posts) {
        view.hiddenProgress();
        view.showUserDetailPosts(posts);
    }

    @Override
    public void onError(String error) {
        view.showError(error);
    }

    public void loadUserPosts(long userId){
        view.startProgress();
        repository.loadUserPosts(this, userId);
    }
}
