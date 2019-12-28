package com.example.madcamp_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    Button btn_red;
    Button btn_blue;
    Button btn_green;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.InitializeView();
        this.SetListener();
    }

    public void InitializeView()
    {
        btn_red = (Button)findViewById(R.id.btn1);
        btn_blue = (Button)findViewById(R.id.btn2);
        btn_green = (Button)findViewById(R.id.btn3);
        textView = (TextView)findViewById(R.id.textView);
    }
    public void SetListener()
    {
        btn_red.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
                //textView.setTextColor(Color.RED);
            }
        });

        btn_blue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                textView.setTextColor(Color.BLUE);
            }
        });

        btn_green.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                textView.setTextColor(Color.GREEN);
            }
        });
    }
}
