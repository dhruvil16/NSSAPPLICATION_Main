package com.dell.nssbvm.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dell.nssbvm.ModalClass.PhotoDetailsModalClass;
import com.dell.nssbvm.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import singleton.openImageInterface;


public class PhotoDisplayActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    ArrayList<PhotoDetailsModalClass> list;
    Context context ;
    openImageInterface openImageInterface ;
    String desc[];
    public PhotoDisplayActivityAdapter(ArrayList<PhotoDetailsModalClass> list,String arr[], Context context,openImageInterface obj) {
        this.list = list;
        this.context = context;
        openImageInterface = obj;
        desc = arr;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        switch (i)
        {
            case R.layout.view_photo_details_fragment:
                 view = LayoutInflater.from(context).inflate(R.layout.view_photo_details_fragment,viewGroup,false);
                 holder = new MyViewHolder(view);
                 break;
            case R.layout.view_extra_note:

                view = LayoutInflater.from(context).inflate(R.layout.view_extra_note,viewGroup,false);
                holder = new ExtraNoteViewHolder(view);
                break;

                default:
               view = LayoutInflater.from(context).inflate(R.layout.view_photo_details_fragment,viewGroup,false);
        }
        return holder;


    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder myViewHolder, int i) {

        if (myViewHolder instanceof ExtraNoteViewHolder)
        {
            if(desc[0].equals("-"))
            {
                ((ExtraNoteViewHolder) myViewHolder).textView1.setVisibility(View.GONE);
            }else {
                for (int j = 0; j < desc.length; j++) {

                    ((ExtraNoteViewHolder) myViewHolder).textView1.setText(desc[j]);
                }
            }


        }
        else if (myViewHolder instanceof MyViewHolder)
        {
            final PhotoDetailsModalClass obj = list.get(i-1);
            Picasso.get().load(obj.getPhoto()).into(((MyViewHolder)myViewHolder).iv);

            ((MyViewHolder)myViewHolder).ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openImageInterface.openImage(obj);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0)
        {

            return R.layout.view_extra_note;
        }
        else
            return R.layout.view_photo_details_fragment;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        LinearLayout ll;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.Id_ImageView_Photo_Details);
            ll = itemView.findViewById(R.id.View_Photo_Details_Fragment_LL_Id);
        }
    }

    public class ExtraNoteViewHolder extends RecyclerView.ViewHolder{
        TextView textView1;
        public ExtraNoteViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textview_id_note_1);
                }


    }
}
