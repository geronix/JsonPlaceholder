package team.max.jsonplaceholder.user.repository;


import android.content.Context;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import team.max.jsonplaceholder.main.database.dao.AddressDAO;
import team.max.jsonplaceholder.main.database.dao.CompanyDAO;
import team.max.jsonplaceholder.main.database.entity.User;
import team.max.jsonplaceholder.main.network.ServiceApiFactori;
import team.max.jsonplaceholder.user.dto.UserDTO;
import team.max.jsonplaceholder.user.mapping.UserMapper;
import team.max.jsonplaceholder.user.repository.api.UserRepository;
import team.max.jsonplaceholder.user.util.sort.SortedUserByName;
import team.max.jsonplaceholder.main.database.dao.UserDAO;

public class UserRepositoryImpl implements UserRepository {

    private UserDAO userDAO;
    private AddressDAO addressDAO;
    private CompanyDAO companyDAO;

    public UserRepositoryImpl(Context context) {
        this.userDAO = new UserDAO(context);
        this.addressDAO = new AddressDAO(context);
        this.companyDAO = new CompanyDAO(context);
    }

    @Override
    public void loadUsers(OnUserListener listener) {
        ServiceApiFactori.getService()
                .getUsers()
                .flatMap(userDTOs -> {
                    List<User> users = UserMapper.mapperUserDTOToUser(userDTOs);
                    deleteUsers();
                    createUsers(users);
                    userDAO.getUsers();
                    return Observable.just(UserMapper.mapperUserToUserDTO(users));
                })
                .onErrorResumeNext(throwable -> {
                    List<User> users = userDAO.getUsers();
                    List<UserDTO> dtos = UserMapper.mapperUserToUserDTO(users);
                    return Observable.just(dtos);
                })
                .map(userDTOs -> {
                    Collections.sort(userDTOs, new SortedUserByName());
                    return userDTOs;})
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listener::onLoadUsersFinished,
                        throwable -> listener.onError(throwable.getMessage()));
    }

    @Override
    public void loadUserPosts(OnUserDetailPostsListener listener, long userId) {
        ServiceApiFactori.getService()
                .getUserPosts(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listener::onLoadUserPostsFinished,
                        throwable -> listener.onError(throwable.getMessage()));
    }

    private void createUsers(List<User> users){
        for(User user : users){
            companyDAO.createCompany(user.getCompany());
            addressDAO.createAddress(user.getAddress());
            userDAO.createUser(user);
        }
    }

    private void deleteUsers(){
        companyDAO.deleteCompany();
        addressDAO.deleteAddress();
        userDAO.deleteUsers();
    }

}
