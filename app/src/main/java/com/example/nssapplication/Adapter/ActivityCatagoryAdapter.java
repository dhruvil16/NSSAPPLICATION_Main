package com.example.nssapplication.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nssapplication.ModalClass.ActivityCatagoryModalClass;
import com.example.nssapplication.R;

import java.util.ArrayList;

public class ActivityCatagoryAdapter extends BaseAdapter {
    Context context;
    ArrayList<ActivityCatagoryModalClass> arrayList;

    public ActivityCatagoryAdapter(Context context, ArrayList<ActivityCatagoryModalClass> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_activity_name_photo,parent,false);
        ImageView imageView = view.findViewById(R.id.Id_Logo_For_View);
        TextView textView = view.findViewById(R.id.Id_Logo_Text_View);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setImageDrawable(context.getDrawable(arrayList.get(position).getPhotoId()));
        }
        textView.setText(arrayList.get(position).getActivityName());
        return view;
    }
}
