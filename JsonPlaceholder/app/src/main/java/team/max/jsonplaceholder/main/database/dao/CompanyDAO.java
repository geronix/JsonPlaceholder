package team.max.jsonplaceholder.main.database.dao;


import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import team.max.jsonplaceholder.main.database.DatabaseHelper;
import team.max.jsonplaceholder.main.database.entity.Company;
import team.max.jsonplaceholder.main.database.DatabaseManager;

public class CompanyDAO {

    private DatabaseHelper databaseHelper;
    private static final String className = CompanyDAO.class.getName();

    public CompanyDAO(Context context) {
        DatabaseManager.getInstance().init(context);
        databaseHelper = DatabaseManager.getInstance().getHelper();
    }

    public void createCompany(Company company){
        try{
            Dao<Company, Long> dao = databaseHelper.getCompanyDao();
            dao.createOrUpdate(company);
        } catch (SQLException e){
            Log.e(className, ".createCompany()", e);
            throw new RuntimeException(e);
        }
    }

    public void deleteCompany(){
        try{
            Dao<Company, Long> dao = databaseHelper.getCompanyDao();
            dao.deleteBuilder().delete();
        } catch (SQLException e){
            Log.e(className, ".deleteCompany()", e);
            throw new RuntimeException(e);
        }
    }

}
