package com.owlscoffeehouse.ormlitedemo.Database;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.owlscoffeehouse.ormlitedemo.Model.Country;
import com.owlscoffeehouse.ormlitedemo.Model.Person;

import java.sql.SQLException;
import java.util.List;

public class DatabaseManager {

    static private DatabaseManager mInstance;

    // The init method should be called at least once before all the database operations
    static public void init(Context ctx) {
        if (null == mInstance) {
            mInstance = new DatabaseManager(ctx);
        }
    }

    static public DatabaseManager getInstance() {
        return mInstance;
    }

    private Context mContext; // Application context
    private DatabaseHelper mDatabaseHelper;

    private DatabaseManager(Context ctx) {
        mContext = ctx.getApplicationContext();
    }

    private DatabaseHelper getDatabaseHelper() {
        if (null == mDatabaseHelper) {
            mDatabaseHelper = new DatabaseHelper(mContext);
        }
        return mDatabaseHelper;
    }

     /***** CRUD functions *****/

    //method for list of person
    public List<Person> GetAllPersons() {
        try {
            Dao<Person, Integer> dao = getDatabaseHelper().getPersonDao();
            List<Person> list = dao.queryForAll();
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //method for insert data
    public int addPerson(Person person) {
        try {
            Dao<Person, Integer> dao = getDatabaseHelper().getPersonDao();
            int i = dao.create(person);
            return i;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    //method for delete all rows
    public void deleteAllPersons() {
        try {
            Dao<Person, Integer> dao = getDatabaseHelper().getPersonDao();
            List<Person> list = dao.queryForAll();
            dao.delete(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /***** CRUD functions *****/

    //method for list of person
    public List<Country> GetAllCountries() {
        try {
            Dao<Country, Integer> dao = getDatabaseHelper().getCountryDao();
            List<Country> list = dao.queryForAll();
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //method for insert data
    public int addCountry(Country country) {
        try {
            Dao<Country, Integer> dao = getDatabaseHelper().getCountryDao();
            int i = dao.create(country);
            return i;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    //method for delete all rows
    public void deleteAllCountries() {
        try {
            Dao<Country, Integer> dao = getDatabaseHelper().getCountryDao();
            List<Country> list = dao.queryForAll();
            dao.delete(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // It is a good practice to call the release() inside the onDestroy() in every Activity class
    // that will perform some database operations
    public void release() {
        if (null != mDatabaseHelper) {
            mDatabaseHelper.close();
            mDatabaseHelper = null;
        }
    }
}
