package com.dell.nssbvm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dell.nssbvm.Adapter.ProgramOfficierAdapter;
import com.dell.nssbvm.ModalClass.ProgramOfficierModalClass;

import java.util.ArrayList;

public class ProgramOfficerActivity extends FragmentThatRespondToBackButton {

    Toolbar toolbar;
    ListView listView;
    ArrayList list;
    ProgramOfficierAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_photo,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        toolbar = getActivity().findViewById(R.id.Toolbar_Id);

        toolbar.setTitle(R.string.Program_officier_activity_string);

        listView = getActivity().findViewById(R.id.Id_Activity_Photo_ListView);

        list = new ArrayList();

        list.add(new ProgramOfficierModalClass(R.drawable.fec_2,"Dr Harshad V Patel","9427856942","hvpatel@bvmengineering.ac.in"));


        list.add(new ProgramOfficierModalClass(R.drawable.fec_1,"MAYURKUMAR M SEVAK","7990687399","mayur.sevak@bvmengineering.ac.in"));


        adapter = new ProgramOfficierAdapter(list,getActivity());

        listView.setAdapter(adapter);
    }

    @Override
    public boolean onBackPressed() {
        toolbar.setTitle(R.string.home_activity_string);
        (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_Id,new home()).commit();
        return true;
    }
}
