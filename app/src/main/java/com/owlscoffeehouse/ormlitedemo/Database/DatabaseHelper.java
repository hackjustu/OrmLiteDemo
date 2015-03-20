package com.owlscoffeehouse.ormlitedemo.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.owlscoffeehouse.ormlitedemo.Model.Country;
import com.owlscoffeehouse.ormlitedemo.Model.Person;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "ormliteandroid.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Person, Integer> mPersonDao = null;
    private Dao<Country, Integer> mCountryDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, Person.class);
            TableUtils.createTable(connectionSource, Country.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Person.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Use this function to access Person DAO
     *
     * @return DAO of the Person Table
     * @throws SQLException
     */
    public Dao<Person, Integer> getPersonDao() throws SQLException {
        if (mPersonDao == null) {
            mPersonDao = getDao(Person.class);
        }
        return mPersonDao;
    }

    /**
     * Use this function to access Country DAO
     *
     * @return DAO of the Country Table
     * @throws SQLException
     */
    public Dao<Country, Integer> getCountryDao() throws SQLException {
        if (mCountryDao == null) {
            mCountryDao = getDao(Country.class);
        }
        return mCountryDao;
    }

    @Override
    public void close() {
        super.close();
        mPersonDao = null;
        mCountryDao = null;
    }
}