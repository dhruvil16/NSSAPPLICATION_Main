package com.dell.nssbvm.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dell.nssbvm.ModalClass.ProgramOfficierModalClass;
import com.dell.nssbvm.R;

import java.util.ArrayList;

public class ProgramOfficierAdapter extends BaseAdapter {

    ArrayList<ProgramOfficierModalClass> arrayList;
    Context context;

    public ProgramOfficierAdapter(ArrayList<ProgramOfficierModalClass> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_program_officer,parent,false);
        TextView name = view.findViewById(R.id.Id_ProgramOfficer_Name);
        TextView email = view.findViewById(R.id.Id_ProgramOfficer_Email);
        TextView phonenumber = view.findViewById(R.id.Id_ProgramOfficer_PhoneNumber);
        ImageView image = view.findViewById(R.id.Id_ProgramOfficer_Photo);

        name.setText(arrayList.get(position).getName());

        email.setText(arrayList.get(position).getEmail());

        phonenumber.setText(arrayList.get(position).getPhoneNumber());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            image.setImageDrawable(context.getDrawable(arrayList.get(position).getPhotoId()));
        }

        return view;
    }
}
