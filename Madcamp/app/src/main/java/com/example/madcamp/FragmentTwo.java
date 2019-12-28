package com.example.madcamp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.madcamp.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


import com.example.madcamp.R;

public class FragmentTwo extends Fragment {
    private ListView listview ;
    private ListViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.frag_layout_2,container,false);




        // Adapter 생성
        adapter = new ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) view.findViewById(R.id.listview1);
        listview.setAdapter(adapter);


        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.screenshot_1577422826),
                "Photo1") ;
        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.screenshot_1577425991),
                "Photo2") ;


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                Drawable iconDrawable = item.getIcon() ;

                Intent intent = new Intent(getActivity().getApplicationContext(), PhotozoomActivity.class);
                Bitmap sendBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.screenshot_1577425991);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                sendBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("image",byteArray);
                startActivity(intent);

                // TODO : use item data.
            }
        }) ;


        return view;
    }
}