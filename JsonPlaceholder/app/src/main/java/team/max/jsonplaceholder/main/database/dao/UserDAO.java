package team.max.jsonplaceholder.main.database.dao;


import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import team.max.jsonplaceholder.main.database.DatabaseHelper;
import team.max.jsonplaceholder.main.database.DatabaseManager;
import team.max.jsonplaceholder.main.database.entity.User;

public class UserDAO {

    private DatabaseHelper databaseHelper;
    private static final String className = UserDAO.class.getName();

    public UserDAO(Context context) {
        DatabaseManager.getInstance().init(context);
        databaseHelper = DatabaseManager.getInstance().getHelper();
    }

    public void createUser(User user){
        try{
            Dao<User, Long> dao = databaseHelper.getUserDao();
            dao.createOrUpdate(user);
        } catch (SQLException e){
            Log.e(className, ".createUser()", e);
            throw new RuntimeException(e);
        }
    }

    public void deleteUsers(){
        try{
            Dao<User, Long> dao = databaseHelper.getUserDao();
            dao.deleteBuilder().delete();
        } catch (SQLException e){
            Log.e(className, ".deleteUsers()", e);
            throw new RuntimeException(e);
        }
    }

    public List<User> getUsers(){
        List<User> list = new ArrayList<>();
        try{
            Dao<User, Long> dao = databaseHelper.getUserDao();
            list = (List<User>) dao.queryForAll();
        } catch (SQLException e){
            Log.e(className, ".getUsers()", e);
            throw new RuntimeException(e);
        }
        return list;
    }

}
