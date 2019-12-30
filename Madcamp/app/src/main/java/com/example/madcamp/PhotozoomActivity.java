package com.example.madcamp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.view.MenuItem;

public class PhotozoomActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photozoom);

        /*ActionBar actionBar = getSupportActionBar();
        //메뉴바에 '<' 버튼이 생긴다.(두개는 항상 같이다닌다)
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);*/

        findViewById(R.id.button1).setOnClickListener(this);


        Intent intent = getIntent();
        byte[] arr = getIntent().getByteArrayExtra("image");
        Bitmap image = BitmapFactory.decodeByteArray(arr, 0, arr.length);
        ImageView BigImage = (ImageView)findViewById(R.id.imageView1);
        BigImage.setImageBitmap(image);
        }



    @Override
    public void onClick(View v) {
        FragmentTwo frag2=new FragmentTwo();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frag_container_,frag2)
                .commit();
    }


}

