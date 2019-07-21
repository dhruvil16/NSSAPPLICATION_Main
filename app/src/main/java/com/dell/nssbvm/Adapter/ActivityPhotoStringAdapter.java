package com.dell.nssbvm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dell.nssbvm.ModalClass.ActivityPhotoModalClass;
import com.dell.nssbvm.R;

import java.util.ArrayList;

public class ActivityPhotoStringAdapter extends BaseAdapter {

    ArrayList<ActivityPhotoModalClass> list;
    Context context;

    public ActivityPhotoStringAdapter(ArrayList<ActivityPhotoModalClass> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_activity_photo_report,parent,false);
        TextView year;

        year = view.findViewById(R.id.Id_Year_View);

        year.setText(list.get(position).getYear());

        return view;

    }
}


