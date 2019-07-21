package com.dell.nssbvm;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class AboutUsFragment extends FragmentThatRespondToBackButton {
    ImageView fb,youtube,insta;
    Toolbar toolbar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.about_us_fragment_view,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    fb = view.findViewById(R.id.fb_logo);
    youtube = view.findViewById(R.id.youtube_logo);
    insta = view.findViewById(R.id.insta_logo);

        toolbar = getActivity().findViewById(R.id.Toolbar_Id);

        toolbar.setTitle(R.string.about_us_activity_string);

        fb.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
            String facebookUrl = getFacebookPageURL(getActivity());
            facebookIntent.setData(Uri.parse(facebookUrl));
            startActivity(facebookIntent);
        }
    });

    youtube.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openYoutube();
        }
    });

    insta.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openInstagram();
        }
    });

        //https://www.facebook.com/CodotaAiForCode/
    }



    public static String FACEBOOK_URL = "https://www.facebook.com/NSSBVM";
    public static String FACEBOOK_PAGE_ID = "CodotaAiForCode";
    public static String YOUTUBE_CHANNEL_LINK ="https://www.youtube.com/channel/UCi2D6Tehmy9X0LST8C_rYyw";
    public static String INSTAGRAM_LINK = "https://instagram.com/teamnssbvm?igshid=157sfkv7zwprq";
    //method to get the right URL to use in the intent
    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }

    private void openInstagram(){

        Uri uri = Uri.parse(INSTAGRAM_LINK);
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(INSTAGRAM_LINK)));
        }
    }

    public void openYoutube(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(YOUTUBE_CHANNEL_LINK));
        startActivity(intent);
    }

    @Override
    public boolean onBackPressed() {
        toolbar.setTitle(R.string.home_activity_string);
        (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_Id,new home()).commit();
        return true;
    }
}
