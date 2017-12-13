package team.max.jsonplaceholder.main.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import team.max.jsonplaceholder.main.database.entity.Address;
import team.max.jsonplaceholder.main.database.entity.Company;
import team.max.jsonplaceholder.main.database.entity.User;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper{

    private static final String DATABASE_NAME = "tzmax_db.db";
    private static final int DATABASE_VERSION = 1;
    private static final String className = DatabaseHelper.class.getName();

    private Dao<User, Long> userDao;
    private Dao<Address, Long> addressDao;
    private Dao<Company, Long> companyDao;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Address.class);
            TableUtils.createTable(connectionSource, Company.class);
        } catch (SQLException e) {
            Log.e(className, "Can't create database - onCreate()", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.dropTable(connectionSource, Address.class, true);
            TableUtils.dropTable(connectionSource, Company.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(className, "Can't drop databases - onUpgrade()", e);
            throw new RuntimeException(e);
        }
    }

    public Dao<User, Long> getUserDao() throws SQLException {
        if (userDao == null) {
            userDao = getDao(User.class);
        }
        return userDao;
    }

    public Dao<Address, Long> getAddressDao() throws SQLException {
        if (addressDao == null) {
            addressDao = getDao(Address.class);
        }
        return addressDao;
    }

    public Dao<Company, Long> getCompanyDao() throws SQLException {
        if (companyDao == null) {
            companyDao = getDao(Company.class);
        }
        return companyDao;
    }

    @Override
    public void close() {
        super.close();
        userDao = null;
        addressDao = null;
        companyDao = null;
    }

}
