package com.example.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String SHARE_PREFERENCES_NAME = "NguyenTienDung";
    private final String MY_NAME = "MY_NAME";
    private final String AGE = "AGE";
    private final String IS_SINGLE = "is_single";
    private final String WEIGH = "weigh";
    //lay ra ten cua class hien tai
    private final String TAG = getClass().getSimpleName();

    Button btn_sava_data;
    Button btn_read_data;
    Button btn_remove_data;
    Button btn_clear_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_sava_data = (Button) findViewById(R.id.btn_save_Data);
        btn_sava_data.setOnClickListener(this);
        btn_read_data = (Button) findViewById(R.id.btn_read_Data);
        btn_read_data.setOnClickListener(this);
        btn_remove_data = (Button) findViewById(R.id.btn_remove_Data);
        btn_remove_data.setOnClickListener(this);
        btn_clear_data = (Button) findViewById(R.id.btn_clear_Data);
        btn_clear_data.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save_Data:
                addData();
                break;
            case R.id.btn_read_Data:
                getData();
                break;
            case R.id.btn_remove_Data:
                removeByKey(MY_NAME);
                getData();
                break;
            case R.id.btn_clear_Data:
                getClears();

                break;
        }


    }




    public void addData() {
        //khoi tao SharedPreferences
        SharedPreferences preferences = getSharedPreferences(SHARE_PREFERENCES_NAME, Context.MODE_PRIVATE);
        // dua du lieu vao SharedPreferences
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(MY_NAME, "Nguyen Tien Dung");
        editor.putInt(AGE, 22);
        editor.putBoolean(IS_SINGLE, false);
        editor.putLong(WEIGH, 52);


        editor.apply();//k dong bo,nhanh hon
        Toast.makeText(this, "thanh cong", Toast.LENGTH_SHORT).show();
        //editor.commit();//dong bo
        //noi luu
//        data/data/[application package name]/shared_prefs/shared_preferences_name.xml

    }

    public void getData() {
        //khoi tao SharedPreferences
        SharedPreferences preferences = getSharedPreferences(SHARE_PREFERENCES_NAME, Context.MODE_PRIVATE);
        String name = preferences.getString(MY_NAME, "Dung");
        int age = preferences.getInt(AGE, 0);
        Boolean is_single = preferences.getBoolean(IS_SINGLE, false);
        long weight = preferences.getLong(WEIGH, 52);
        String address = preferences.getString("address", "Ha Noi");

        Log.d(TAG, "Name : " + name + "\n" + " age : " + age + "\n" + "is_single" + is_single + "\n" + "weight" + weight + "\n" + "address " + address);

    }

    private void removeByKey(String key) {
        SharedPreferences preferences = getSharedPreferences(SHARE_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.remove(key);
        editor.apply();
    }
    private void getClears() {
        SharedPreferences preferences = getSharedPreferences(SHARE_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.clear();
        //cap nhat du lieu
        editor.apply();
    }
}