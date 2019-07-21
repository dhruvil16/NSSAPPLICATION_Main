package com.dell.nssbvm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class OpenImageFullScreen extends FragmentThatRespondToBackButton{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.full_screen_image_display,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Toolbar toolbar = getActivity().findViewById(R.id.Toolbar_Id);

        toolbar.setVisibility(View.GONE);

            String url = getArguments().getString("IMAGE_URL");

            ImageView iv = view.findViewById(R.id.Full_Screen_ImageView_Id);

        Picasso.get().load(url).into(iv);

    }

    @Override
    public boolean onBackPressed() {
        DisplayPhoto displayPhoto = new DisplayPhoto();

        Bundle bundle = new Bundle();
        bundle.putInt("Id_Year",getArguments().getInt("Id_Year"));
        bundle.putString("Id_Catagory",getArguments().getString("Id_Catagory"));

        displayPhoto.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_Id,displayPhoto).commit();
        return true;
    }
}
