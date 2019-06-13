package com.example.nssapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nssapplication.ModalClass.PhotoDetailsModalClass;
import com.example.nssapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class PhotoDisplayActivityAdapter extends RecyclerView.Adapter<PhotoDisplayActivityAdapter.MyViewHolder>{

    ArrayList<PhotoDetailsModalClass> list;
    Context context ;

    public PhotoDisplayActivityAdapter(ArrayList<PhotoDetailsModalClass> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

       View view = LayoutInflater.from(context).inflate(R.layout.view_photo_details_fragment,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
                PhotoDetailsModalClass obj = list.get(i);

        Picasso.get().load(obj.getPhoto()).into(myViewHolder.iv);

        myViewHolder.tv.setText(obj.getDetials());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            iv = itemView.findViewById(R.id.Id_ImageView_Photo_Details);
            tv = itemView.findViewById(R.id.Id_TextView_Photo_Details);
        }


    }
}
