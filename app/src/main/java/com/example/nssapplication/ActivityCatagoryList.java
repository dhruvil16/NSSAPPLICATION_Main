package com.example.nssapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nssapplication.Adapter.ActivityCatagoryAdapter;
import com.example.nssapplication.ModalClass.ActivityCatagoryModalClass;

import java.util.ArrayList;

public class ActivityCatagoryList extends Fragment {

    ArrayList<ActivityCatagoryModalClass> list;
    ListView listView;
    int year_id;
    ActivityCatagoryAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_photo,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        year_id = getArguments().getInt("year_entry_id",0);

        listView = getActivity().findViewById(R.id.Id_Activity_Photo_ListView);

        list = new ArrayList<ActivityCatagoryModalClass>();
        list.add(new ActivityCatagoryModalClass(R.drawable.ic_launcher_background,"Tree Plantation"));
        list.add(new ActivityCatagoryModalClass(R.drawable.ic_launcher_background,"Blood Donation Camp"));
        list.add(new ActivityCatagoryModalClass(R.drawable.ic_launcher_background,"Tree plantation"));
        list.add(new ActivityCatagoryModalClass(R.drawable.ic_launcher_background,"Blood Donation Camp"));
        list.add(new ActivityCatagoryModalClass(R.drawable.ic_launcher_background,"Tree Plantation"));

        adapter = new ActivityCatagoryAdapter(getActivity(),list);

        listView.setAdapter(adapter);



        // based on Year we give different content to be displayed and also based on catagory on which clicked here
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                swi



                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_Id,new DisplayPhoto()).commit();
            }
        });
    }



}
