package com.dell.nssbvm;

import android.support.v4.app.Fragment;

public class FragmentThatRespondToBackButton extends Fragment {

    public boolean onBackPressed(){
        return false;
    }

}
