package com.dell.nssbvm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dell.nssbvm.Adapter.ActivityCatagoryAdapter;
import com.dell.nssbvm.ModalClass.ActivityCatagoryModalClass;

import java.util.ArrayList;

public class ActivityCatagoryList extends FragmentThatRespondToBackButton {

    ArrayList<ActivityCatagoryModalClass> list;
    ListView listView;
    ActivityCatagoryAdapter adapter;
    int year;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_photo,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();

        year = bundle.getInt("Id_Year");

        listView = getActivity().findViewById(R.id.Id_Activity_Photo_ListView);

        list = new ArrayList<ActivityCatagoryModalClass>();

         if(year == 0)
        {

            list.add(new ActivityCatagoryModalClass(R.drawable.blood_donation,"Blood Donation Camp"));
            list.add(new ActivityCatagoryModalClass(R.drawable.vilage_camp,"Village Camp"));
            list.add(new ActivityCatagoryModalClass(R.drawable.republic_day,"Republic Day"));
        }
       else if(year==1)
        {
            list.add(new ActivityCatagoryModalClass(R.drawable.tree_plantation,"Tree Plantation"));
            list.add(new ActivityCatagoryModalClass(R.drawable.help_desk,"Help Desk for New Student"));
            list.add(new ActivityCatagoryModalClass(R.drawable.independence_day,"Independence Day"));
            list.add(new ActivityCatagoryModalClass(R.drawable.blood_donation,"Blood Donation Camp-1"));
            list.add(new ActivityCatagoryModalClass(R.drawable.swin_flu,"Swine Flu Ukala Camp"));
            list.add(new ActivityCatagoryModalClass(R.drawable.tablet_distibution,"Tablet Distribution & Thalassemia Awareness"));
            list.add(new ActivityCatagoryModalClass(R.drawable.thelesemia,"Thalassemia Chekup Camp"));
            list.add(new ActivityCatagoryModalClass(R.drawable.voting_awareness,"Voting Awareness"));
            list.add(new ActivityCatagoryModalClass(R.drawable.blood_donation,"Blood Donation Camp-2"));
            list.add(new ActivityCatagoryModalClass(R.drawable.republic_day,"Republic Day"));
            list.add(new ActivityCatagoryModalClass(R.drawable.soldier_walfare,"Sainik Welfare Fund"));

            list.add(new ActivityCatagoryModalClass(R.drawable.confidenece_busting,"Confidence Boosting Event for SSC Students"));

            list.add(new ActivityCatagoryModalClass(R.drawable.vilage_camp,"Village Camp"));
        }

        else if(year ==2 )
        {
            list.add(new ActivityCatagoryModalClass(R.drawable.plant_survy,"Plant Survey"));
            list.add(new ActivityCatagoryModalClass(R.drawable.help_desk,"Help Desk"));
            list.add(new ActivityCatagoryModalClass(R.drawable.nss_orientation,"NSS Orientation Program"));
            list.add(new ActivityCatagoryModalClass(R.drawable.tree_plantation,"Tree Plantation"));
            list.add(new ActivityCatagoryModalClass(R.drawable.blood_donation,"Blood Donation Camp-1"));
            list.add(new ActivityCatagoryModalClass(R.drawable.independence_day,"Independence Day"));
            list.add(new ActivityCatagoryModalClass(R.drawable.blindfundcollection,"Fund Collection For Blind"));
            list.add(new ActivityCatagoryModalClass(R.drawable.thelesemia,"Thalassemia Chekup Camp"));
            list.add(new ActivityCatagoryModalClass(R.drawable.sba,"Swachh Bharat Abhiyan"));
            list.add(new ActivityCatagoryModalClass(R.drawable.blood_donation,"Blood Donation Camp-2"));
            list.add(new ActivityCatagoryModalClass(R.drawable.tablet_distibution,"Tablet Distribution"));
            list.add(new ActivityCatagoryModalClass(R.drawable.blood_donation,"Blood Donation Camp-3"));
            list.add(new ActivityCatagoryModalClass(R.drawable.vilage_camp,"Village Camp"));
            list.add(new ActivityCatagoryModalClass(R.drawable.blood_donation,"Blood Donation Camp-4"));
        }

        else{
            list.add(new ActivityCatagoryModalClass(R.drawable.yoga_day,"Yoga Day"));
        }

        adapter = new ActivityCatagoryAdapter(getActivity(),list);

        listView.setAdapter(adapter);



        // based on Year we give different content to be displayed and also based on catagory on which clicked here
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DisplayPhoto displayPhoto = new DisplayPhoto();

                Bundle bundle = new Bundle();

                bundle.putInt("Id_Year",year);

                ActivityCatagoryModalClass obj = (ActivityCatagoryModalClass) list.get(position);

                bundle.putString("Id_Catagory",obj.getActivityName());

                displayPhoto.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_Id,displayPhoto).commit();

            }
        });
    }

    @Override
    public boolean onBackPressed() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_Id,new Activity_Photo()).commit();
        return true;
    }

}
