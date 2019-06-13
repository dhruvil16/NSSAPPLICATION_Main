package com.example.nssapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nssapplication.Adapter.PhotoDisplayActivityAdapter;
import com.example.nssapplication.ModalClass.PhotoDetailsModalClass;

import java.util.ArrayList;

public class DisplayPhoto extends Fragment {

    ArrayList<PhotoDetailsModalClass> list;
    PhotoDisplayActivityAdapter adapter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_display_photo,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = getActivity().findViewById(R.id.Id_RecyclerView_PhotoDisplay);

        list = new ArrayList<PhotoDetailsModalClass>();

        list.add(new PhotoDetailsModalClass("https://735ad2a6-a-62cb3a1a-s-sites.googlegroups.com/site/anycarknowledge/Home/bugatti-veyron.jpg","CAR"));

        adapter = new PhotoDisplayActivityAdapter(list,getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        recyclerView.setAdapter(adapter);

    }
}
