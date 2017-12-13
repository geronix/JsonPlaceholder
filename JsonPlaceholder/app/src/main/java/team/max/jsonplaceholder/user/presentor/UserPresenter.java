package team.max.jsonplaceholder.user.presentor;


import android.content.Context;

import java.util.List;

import team.max.jsonplaceholder.user.dto.UserDTO;
import team.max.jsonplaceholder.user.repository.api.UserRepository;
import team.max.jsonplaceholder.user.view.api.UsersView;
import team.max.jsonplaceholder.user.repository.UserRepositoryImpl;

public class UserPresenter implements UserRepository.OnUserListener {

    private UsersView view;
    private UserRepository repository;

    public UserPresenter(UsersView view, Context context) {
        repository = new UserRepositoryImpl(context);
        this.view = view;
    }

    public void loadUsers(){
        view.startProgress();
        repository.loadUsers(this);
    }

    @Override
    public void onLoadUsersFinished(List<UserDTO> users) {
        view.showUsers(users);
        view.hiddenProgress();
    }

    @Override
    public void onError(String error) {
        view.showError(error);
    }
}
