package com.example.nssapplication;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nssapplication.Adapter.ActivityCatagoryAdapter;
import com.example.nssapplication.Adapter.ActivityPhotoStringAdapter;
import com.example.nssapplication.ModalClass.ActivityPhotoModalClass;

import java.util.ArrayList;

public class Activity_Photo  extends Fragment {

    ListView listView;
    Toolbar toolbar;
    ArrayList<ActivityPhotoModalClass> list;
    ActivityPhotoStringAdapter adapter ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_photo,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        toolbar = getActivity().findViewById(R.id.Toolbar_Id);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle(R.string.photo_activity_string);
         }

        listView = (getActivity()).findViewById(R.id.Id_Activity_Photo_ListView);

        list = new ArrayList<ActivityPhotoModalClass>();

        list.add(new ActivityPhotoModalClass("2014-2015"));
        list.add(new ActivityPhotoModalClass("2015-2016"));
        list.add(new ActivityPhotoModalClass("2016-2017"));
        list.add(new ActivityPhotoModalClass("2017-2018"));
        list.add(new ActivityPhotoModalClass("2018-2019"));

        adapter = new ActivityPhotoStringAdapter(list,getActivity());




        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Bundle bundle = new Bundle();
                bundle.putInt("year_entry_id",position);

                ActivityCatagoryList obj = new ActivityCatagoryList();

                // position on which clicked is passed in bundle to ActivityCatagoryList

                obj.setArguments(bundle);

                Toast.makeText(getActivity(),"id "+id+" position "+position,Toast.LENGTH_SHORT).show();

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_Id,obj).commit();

            }
        });

    }
}
