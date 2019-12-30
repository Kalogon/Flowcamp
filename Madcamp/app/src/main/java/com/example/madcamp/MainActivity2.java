package com.example.madcamp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;


public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {


    String strNickname, strProfile;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        /*TextView tvProfile = findViewById(R.id.tvProfile);*/

        Intent intent = getIntent();
        strNickname = intent.getStringExtra("name");
        /*strProfile = intent.getStringExtra("profile");*/
        tb.setTitle(strNickname);
        /*tvNickname.setText(strNickname);
        tvProfile.setText(strProfile);*/

        findViewById(R.id.btn_frag1).setOnClickListener(this);
        findViewById(R.id.btn_frag2).setOnClickListener(this);
        findViewById(R.id.btn_frag3).setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menu_logout:
                Toast.makeText(getApplicationContext(), "정상적으로 로그아웃되었습니다.", Toast.LENGTH_SHORT).show();
                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
                break;
            case R.id.menu_account:
                break;
            case R.id.menu_search:
                break;
        }
        return true;
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





