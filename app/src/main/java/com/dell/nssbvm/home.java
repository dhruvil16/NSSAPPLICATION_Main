package com.dell.nssbvm;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dell.nssbvm.Adapter.SliderAdapter;

import java.util.ArrayList;
import java.util.List;

public class home extends FragmentThatRespondToBackButton {


    ViewPager viewPager;
    TabLayout indicator;

    List<Integer> img_list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        viewPager=(ViewPager)(getActivity()).findViewById(R.id.viewPager);
        indicator=(TabLayout)getActivity().findViewById(R.id.indicator);
        img_list = new ArrayList<>();
        img_list.add(R.drawable.img_3_slide);
        img_list.add(R.drawable.img_5_slide);
        img_list.add(R.drawable.img_6_slide);
        img_list.add(R.drawable.img_1_slide);
        img_list.add(R.drawable.img_2_slide);
        img_list.add(R.drawable.img_4_slide);

        viewPager.setAdapter(new SliderAdapter(getActivity(),img_list));
        indicator.setupWithViewPager(viewPager, true);
    }

    @Override
    public boolean onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getActivity().finishAffinity();
        }
        return false;
    }
}
