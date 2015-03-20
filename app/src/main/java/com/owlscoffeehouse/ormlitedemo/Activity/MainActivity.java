package com.owlscoffeehouse.ormlitedemo.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.owlscoffeehouse.ormlitedemo.Database.DatabaseHelper;
import com.owlscoffeehouse.ormlitedemo.Database.DatabaseManager;
import com.owlscoffeehouse.ormlitedemo.Adapter.NameAdapter;
import com.owlscoffeehouse.ormlitedemo.Model.Person;
import com.owlscoffeehouse.ormlitedemo.R;

import java.util.List;


public class MainActivity extends ActionBarActivity
        implements AdapterView.OnItemClickListener {

    EditText mEntry;
    ListView mListView;
    NameAdapter mNameAdapter = null;
    DatabaseHelper mDatabaseHelper;
    List<Person> mPersonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DatabaseManager.init(this);
        loadInitialData();

        mListView = (ListView) findViewById(R.id.listView1);
        mListView.setOnItemClickListener(this);

        mEntry = (EditText) findViewById(R.id.etentry);

        mDatabaseHelper = new DatabaseHelper(getApplicationContext());

        setDataToAdapter();
    }

    public void addPerson(View v) {
        String strName = mEntry.getText().toString().trim();
        if (TextUtils.isEmpty(strName)) {
            showToast("Please Add your Name!!!");
            return;
        }

        Person person = new Person(strName);

        DatabaseManager.getInstance().addPerson(person);

        showToast("Data Successfully Added");

        setDataToAdapter();
    }

    private void setDataToAdapter() {

        mPersonList = DatabaseManager.getInstance().GetAllPersons();

        mNameAdapter = new NameAdapter(this, R.layout.row, mPersonList);
        mListView.setAdapter(mNameAdapter);
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void deleteData(View v) {

        mPersonList = DatabaseManager.getInstance().GetAllPersons();

        if (null != mPersonList && mPersonList.size() > 0) {
            AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
                    .create();
            alert.setTitle("Delete ?");
            alert.setMessage("Are you sure want to delete All data from Database");

            alert.setButton("No", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alert.setButton2("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                    DatabaseManager.getInstance().deleteAllPersons();
                    mEntry.setText("");
                    showToast("Removed All Data!!!");

                    setDataToAdapter();
                }
            });
            alert.show();
        } else {
            showToast("No data found from the Database");
        }
    }

    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        showToast(mPersonList.get(position).getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadInitialData() {

        DatabaseManager.getInstance().addPerson(new Person("Job Steve"));
        DatabaseManager.getInstance().addPerson(new Person("Larry Page"));
        DatabaseManager.getInstance().addPerson(new Person("Elon Musk"));
        DatabaseManager.getInstance().addPerson(new Person("Bill Gates"));
        DatabaseManager.getInstance().addPerson(new Person("Harry Porter"));
        DatabaseManager.getInstance().addPerson(new Person("Taylor Swift"));
    }

    @Override
    protected void onDestroy() {
        DatabaseManager.getInstance().release();
        super.onDestroy();
    }
}
