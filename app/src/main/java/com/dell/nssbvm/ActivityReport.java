package com.dell.nssbvm;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dell.nssbvm.Adapter.ActivityPhotoStringAdapter;
import com.dell.nssbvm.ModalClass.ActivityPhotoModalClass;

import java.util.ArrayList;

public class ActivityReport extends FragmentThatRespondToBackButton {

    Toolbar toolbar;
    ListView listview;
    ArrayList<ActivityPhotoModalClass> list;
    ActivityPhotoStringAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_photo,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        toolbar = getActivity().findViewById(R.id.Toolbar_Id);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle(R.string.report_activty_string);
        }


        listview = getActivity().findViewById(R.id.Id_Activity_Photo_ListView);
        list = new ArrayList<>();

        list.add(new ActivityPhotoModalClass("2016-2017"));
        list.add(new ActivityPhotoModalClass("2017-2018"));
        list.add(new ActivityPhotoModalClass("2018-2019"));
        list.add(new ActivityPhotoModalClass("2019-2020"));

        adapter = new ActivityPhotoStringAdapter(list,getActivity());

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DisplayListOfReport displayListOfReport = new DisplayListOfReport();
                Bundle bundle = new Bundle();
                bundle.putInt("year",position);
                displayListOfReport.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_Id,displayListOfReport).commit();
            }
        });

    }

    @Override
    public boolean onBackPressed() {
        toolbar.setTitle(R.string.home_activity_string);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_Id,new home()).commit();
        return true;
    }
}
