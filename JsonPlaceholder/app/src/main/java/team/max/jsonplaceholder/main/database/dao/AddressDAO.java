package team.max.jsonplaceholder.main.database.dao;


import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import team.max.jsonplaceholder.main.database.DatabaseHelper;
import team.max.jsonplaceholder.main.database.entity.Address;
import team.max.jsonplaceholder.main.database.DatabaseManager;

public class AddressDAO {

    private DatabaseHelper databaseHelper;
    private static final String className = AddressDAO.class.getName();

    public AddressDAO(Context context) {
        DatabaseManager.getInstance().init(context);
        databaseHelper = DatabaseManager.getInstance().getHelper();
    }

    public void createAddress(Address address){
        try{
            Dao<Address, Long> dao = databaseHelper.getAddressDao();
            dao.createOrUpdate(address);
            long id = address.getAddressId();
        } catch (SQLException e){
            Log.e(className, ".createAddress()", e);
            throw new RuntimeException(e);
        }
    }

    public void deleteAddress(){
        try{
            Dao<Address, Long> dao = databaseHelper.getAddressDao();
            dao.deleteBuilder().delete();
        } catch (SQLException e){
            Log.e(className, ".deleteAddress()", e);
            throw new RuntimeException(e);
        }
    }

}
