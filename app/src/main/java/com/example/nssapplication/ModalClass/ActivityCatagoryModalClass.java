package com.example.nssapplication.ModalClass;

public class ActivityCatagoryModalClass {
    int PhotoId;
    String ActivityName;

    public ActivityCatagoryModalClass(int photoId, String activityName) {
        PhotoId = photoId;
        ActivityName = activityName;
    }

    public int getPhotoId() {
        return PhotoId;
    }

    public String getActivityName() {
        return ActivityName;
    }
}
