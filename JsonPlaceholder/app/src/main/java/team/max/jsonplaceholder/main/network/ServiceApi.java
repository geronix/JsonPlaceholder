package team.max.jsonplaceholder.main.network;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import team.max.jsonplaceholder.user.dto.PostDTO;
import team.max.jsonplaceholder.user.dto.UserDTO;

public interface ServiceApi {

    @GET("users")
    Observable<List<UserDTO>> getUsers();

    @GET("posts")
    Observable<List<PostDTO>> getUserPosts(@Query("userId") long userId);

}
