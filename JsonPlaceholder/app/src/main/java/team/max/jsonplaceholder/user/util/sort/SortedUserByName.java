package team.max.jsonplaceholder.user.util.sort;


import java.util.Comparator;

import team.max.jsonplaceholder.user.dto.UserDTO;

public class SortedUserByName implements Comparator<UserDTO> {

    @Override
    public int compare(UserDTO dto, UserDTO t1) {
        return dto.getName().compareToIgnoreCase(t1.getName());
    }
}
