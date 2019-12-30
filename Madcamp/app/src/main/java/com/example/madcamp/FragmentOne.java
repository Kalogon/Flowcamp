package com.example.madcamp;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.madcamp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class FragmentOne extends Fragment {

    public ArrayList<Phonenumber> dataList;
    private ListView mListview;
    private Button mBtnAddress;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.frag_layout_1,container,false);

        mListview = (ListView) view.findViewById(R.id.listview);
        mBtnAddress = (Button) view.findViewById(R.id.btnAddress);
        mBtnAddress.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                switch (v.getId()) {
                    case R.id.btnAddress:

                        dataList = new ArrayList<Phonenumber>();
                        Cursor c = ((MainActivity2)getActivity()).getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                                null, null, null,
                                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " asc");


                        while (c.moveToNext()) {
                            // 연락처 id 값
                            String id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                            // 연락처 대표 이름
                            String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
                            String number;

                            // ID로 전화 정보 조회
                            Cursor phoneCursor = ((MainActivity2)getActivity()).getContentResolver().query(
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                    null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                                    null, null);

                            // 데이터가 있는 경우
                            if (phoneCursor.moveToFirst()) {
                                number = phoneCursor.getString(phoneCursor.getColumnIndex(
                                        ContactsContract.CommonDataKinds.Phone.NUMBER));

                            }
                            else{
                                number="None";
                            }
                            Phonenumber p = new Phonenumber(name,number);
                            phoneCursor.close();
                            dataList.add(p);
                        }// end while
                        Collections.sort(dataList, new Comparator<Phonenumber>() {
                            @Override
                            public int compare(Phonenumber s1, Phonenumber s2) {
                                return s1.getName().toUpperCase().compareTo(s2.getName().toUpperCase());
                            }
                        });
                        c.close();
                        ArrayList Templist = new ArrayList<Map<String, String>>();


                        for(Phonenumber p:dataList){
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("name", p.getName());
                            map.put("phone", p.getNumber());
                            Templist.add(map);
                        }

                        SimpleAdapter adapter = new SimpleAdapter(getActivity(),
                                Templist,
                                android.R.layout.simple_list_item_2,
                                new String[]{"name", "phone"},
                                new int[]{android.R.id.text1, android.R.id.text2});
                        mListview.setAdapter(adapter);
                }

            }

        });


        return view;
    }
    class Phonenumber {
        String name;
        String number;

        public Phonenumber(String name, String number) {
            this.name = name;
            this.number = number;
        }

        public String getName() {
            return this.name;
        }

        public String getNumber() {
            return this.number;
        }
    }

}

