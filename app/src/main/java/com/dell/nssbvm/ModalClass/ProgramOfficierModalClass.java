package com.dell.nssbvm.ModalClass;

public class ProgramOfficierModalClass {
    int photo_id;
    String name;
    String phoneNumber;
    String email;

    public ProgramOfficierModalClass(int photo_id,String name, String phoneNumber, String email) {
        this.photo_id = photo_id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getPhotoId(){return photo_id;};
}
