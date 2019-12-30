package com.example.madcamp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_frag1).setOnClickListener(this);
        findViewById(R.id.btn_frag2).setOnClickListener(this);
        findViewById(R.id.btn_frag3).setOnClickListener(this);

        /*try{
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String key = new String(Base64.encode(md.digest(), 0));
                Log.d("Hash key", key+"!!!!!!");
            }
        } catch (Exception e){
            Log.e("name not found", e.toString());
        }*/


    }

    @Override
    public void onClick(View v) {
        FragmentOne frag1=new FragmentOne();
        FragmentTwo frag2=new FragmentTwo();
        FragmentThree frag3=new FragmentThree();

        switch (v.getId()) {
            case R.id.btn_frag1:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frag_container_,frag1)
                        .commit();
                break;
            case R.id.btn_frag2:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frag_container_,frag2)
                        .commit();
                break;
            case R.id.btn_frag3:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frag_container_,frag3)
                        .commit();
                break;

        }
    }


}





