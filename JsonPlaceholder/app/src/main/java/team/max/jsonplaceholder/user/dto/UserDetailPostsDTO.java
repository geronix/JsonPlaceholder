package team.max.jsonplaceholder.user.dto;


import java.io.Serializable;
import java.util.List;

public class UserDetailPostsDTO implements Serializable{

    private UserDTO user;
    private List<PostDTO> posts;

    public UserDetailPostsDTO() {
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }
}
