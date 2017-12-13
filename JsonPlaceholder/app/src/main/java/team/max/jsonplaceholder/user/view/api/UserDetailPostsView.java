package team.max.jsonplaceholder.user.view.api;


import java.util.List;

import team.max.jsonplaceholder.main.view.api.MainView;
import team.max.jsonplaceholder.user.dto.PostDTO;

public interface UserDetailPostsView extends MainView {

    void showUserDetailPosts(List<PostDTO> posts);

}
