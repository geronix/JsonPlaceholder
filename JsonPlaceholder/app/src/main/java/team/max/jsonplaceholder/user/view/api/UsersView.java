package team.max.jsonplaceholder.user.view.api;


import java.util.List;

import team.max.jsonplaceholder.main.view.api.MainView;
import team.max.jsonplaceholder.user.dto.UserDTO;

public interface UsersView extends MainView {

    void showUsers(List<UserDTO> users);

}
