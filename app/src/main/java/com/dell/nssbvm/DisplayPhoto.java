package com.dell.nssbvm;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dell.nssbvm.Adapter.PhotoDisplayActivityAdapter;
import com.dell.nssbvm.ModalClass.PhotoDetailsModalClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import singleton.openImageInterface;

public class DisplayPhoto extends FragmentThatRespondToBackButton{

    ArrayList<PhotoDetailsModalClass> list;
    PhotoDisplayActivityAdapter adapter;
    RecyclerView recyclerView;
    int id_year;
    String id_catagory;
    String url , desc[];


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_display_photo,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = getActivity().findViewById(R.id.Id_ListView_PhotoDisplay);
        list = new ArrayList<PhotoDetailsModalClass>();

        id_year =  getArguments().getInt("Id_Year");

  //      Toast.makeText(getActivity(), " id_year "+id_year, Toast.LENGTH_SHORT).show();

        id_catagory = getArguments().getString("Id_Catagory");


        getData();


    }

    public void getData()
    {

        String y = String.valueOf((id_year+2016));
        String e = id_catagory.replace(" ","");
        url = "https://fast-peak-96163.herokuapp.com/getPhotos?year="+y+"&event="+e;//"https://fast-peak-96163.herokuapp.com/getPhotos?year=2017&event=Independence%20Day";

        final ProgressDialog dialog = new ProgressDialog(getActivity());

        dialog.setMessage("Loading Images");

        dialog.show();

        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());//VollySingleton.getInstance(getActivity()).getRequestQueue();


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(JsonObjectRequest.Method.GET , url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    dialog.dismiss();

                    Log.e("TAG", "onResponse: inside response ");

                    String status = (String) response.get("status");

                    if(status.equals("Yes"))
                    {
                        JSONArray jsonArray = response.getJSONArray("img");
                        for (int i = 0; i <jsonArray.length(); i++) {
                            String img_url  =  jsonArray.getString(i);
                            list.add(new PhotoDetailsModalClass(img_url));
                            Log.d("TAG", "--------->>  "+img_url+"<<------- ");
                        }

                        jsonArray = response.getJSONArray("desc");

                        desc = new String[jsonArray.length()];

                        for (int i = 0; i < jsonArray.length(); i++) {
                            desc[i] = jsonArray.getString(i);
                        }

                    }
                    else{
                        Toast.makeText(getActivity(), " Something Went Wrong ", Toast.LENGTH_SHORT).show();
                    }

                    // setUp Adapter

                    adapter = new PhotoDisplayActivityAdapter(list,desc, getActivity(), new openImageInterface() {
                        @Override
                        public void openImage(PhotoDetailsModalClass obj) {

                            OpenImageFullScreen openImageFullScreen = new OpenImageFullScreen();

                            Bundle bundle = new Bundle();

                            bundle.putString("IMAGE_URL",obj.getPhoto());

                            /*
                                below two bundle are used for coming back to old fragment
                             */
                            bundle.putString("Id_Catagory",id_catagory);

                            bundle.putInt("Id_Year",id_year);

                            openImageFullScreen.setArguments(bundle);

//                            Toast.makeText(getActivity(), "this is called........... ", Toast.LENGTH_SHORT).show();

                            (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_Id,openImageFullScreen).commit();

                        }
                    });

                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();

            }
        });

        requestQueue.add(jsonObjectRequest);

    }

    @Override
    public boolean onBackPressed() {
        ActivityCatagoryList obj = new ActivityCatagoryList();
        Bundle bundle = new Bundle();
        bundle.putInt("Id_Year",id_year);
        obj.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_Id,obj).commit();
        return true;
    }
}
