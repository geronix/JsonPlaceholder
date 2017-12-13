package team.max.jsonplaceholder.user.repository.api;


import java.util.List;

import team.max.jsonplaceholder.user.dto.PostDTO;
import team.max.jsonplaceholder.user.dto.UserDTO;

public interface UserRepository {

    interface OnError {
        void onError(String error);
    }

    interface OnUserListener extends OnError{
        void onLoadUsersFinished(List<UserDTO> users);
    }

    interface OnUserDetailPostsListener extends OnError{
        void onLoadUserPostsFinished(List<PostDTO> posts);
    }

    void loadUsers(OnUserListener listener);

    void loadUserPosts(OnUserDetailPostsListener listener, long userId);
}
