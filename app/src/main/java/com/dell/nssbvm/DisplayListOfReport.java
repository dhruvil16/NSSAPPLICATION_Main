package com.dell.nssbvm;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.dell.nssbvm.Adapter.ActivityPhotoStringAdapter;
import com.dell.nssbvm.ModalClass.ActivityPhotoModalClass;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

import static android.media.CamcorderProfile.get;

public class DisplayListOfReport extends FragmentThatRespondToBackButton implements EasyPermissions.PermissionCallbacks {
    ListView listView;
    ArrayList list;
    String filename;
    ActivityPhotoStringAdapter adapter;
    private static final int WRITE_REQUEST_CODE = 300;

    // download_url is the url for downloading report
    String download_url;
    Boolean FileAlreadyDownloaded = false;
    int year;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_photo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle bundle;
        list = new ArrayList();

        if(Build.VERSION.SDK_INT>=24){
            try{
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        listView = getActivity().findViewById(R.id.Id_Activity_Photo_ListView);

         year = getArguments().getInt("year");


        if (year == 0) {
            list.add(new ActivityPhotoModalClass("Annual Report"));

        } else if (year == 1) {
            list.add(new ActivityPhotoModalClass("Blood Donation"));
            list.add(new ActivityPhotoModalClass("Indepence Day"));
            list.add(new ActivityPhotoModalClass("Ukala Event"));
            list.add(new ActivityPhotoModalClass("Village Camp"));

        } else if(year==2) {
            list.add(new ActivityPhotoModalClass("Blood Donation-1"));
            list.add(new ActivityPhotoModalClass("Fund Collection For Blind"));
            list.add(new ActivityPhotoModalClass("Blood Donation-2"));
            list.add(new ActivityPhotoModalClass("Blood Donation-3"));
            list.add(new ActivityPhotoModalClass("Help Desk"));
            list.add(new ActivityPhotoModalClass("Indepence Day"));
            list.add(new ActivityPhotoModalClass("Orientation"));
            list.add(new ActivityPhotoModalClass("Village Camp"));
            list.add(new ActivityPhotoModalClass("Swachh Bharat Abhiyan"));
            list.add(new ActivityPhotoModalClass("Tablet Distribution"));
            list.add(new ActivityPhotoModalClass("Thalassemia Camp"));
            list.add(new ActivityPhotoModalClass("Tree Plantation"));
            list.add(new ActivityPhotoModalClass("Annual Report"));

        }
        else{
            list.add(new ActivityPhotoModalClass("Yoga Day"));
        }

        adapter = new ActivityPhotoStringAdapter(list, getActivity());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
  //              download_url = "https://firebasestorage.googleapis.com/v0/b/nssapplication-fe86c.appspot.com/o/File(2017-18)%2Fblood_donation__camp%5B1%5D.docx?alt=media&token=cb93e037-f256-4f10-a352-0b0595596292";


                ActivityPhotoModalClass obj = (ActivityPhotoModalClass) list.get(position);

                // file name which must be searched
                filename = obj.getYear();

                if(year==0)
                {
                    if(filename.equals("Annual Report"))
                    download_url = "https://firebasestorage.googleapis.com/v0/b/nssapplication-fe86c.appspot.com/o/File(2016-17)%2FNational%20Service%20Scheme%20Annual%20Report%20of%20Academic%20Year%202016.pdf?alt=media&token=f07e183b-79a9-4326-8ecb-45a52dcffe3e";
                }
                else if(year==1)
                {
                    switch (position) {
                        case 0:
                            download_url="https://firebasestorage.googleapis.com/v0/b/nssapplication-fe86c.appspot.com/o/File(2017-18)%2Fblood_donation__camp%5B1%5D.pdf?alt=media&token=9af94a80-fd8d-4d57-b8cc-7d112c88365b";
                            break;
                        case 1:
                            download_url="https://firebasestorage.googleapis.com/v0/b/nssapplication-fe86c.appspot.com/o/File(2017-18)%2Findependence%20day%20report_.pdf?alt=media&token=069c23a9-c505-41da-b096-6801de368238";
                            break;
                        case 2:
                            download_url="https://firebasestorage.googleapis.com/v0/b/nssapplication-fe86c.appspot.com/o/File(2017-18)%2Fukala%20report.pdf?alt=media&token=fc777b7e-0a60-45c5-9500-c8094a0d54ca";
                            break;
                        case 3:
                            download_url="https://firebasestorage.googleapis.com/v0/b/nssapplication-fe86c.appspot.com/o/File(2017-18)%2FVillage%20camp.pdf?alt=media&token=0f6543c7-4195-46dd-9d19-86a82ac988fd";
                            break;

                    }
                }
                else if(year==2) {

                    switch (position) {
                        case 0:
                            download_url="https://firebasestorage.googleapis.com/v0/b/nssapplication-fe86c.appspot.com/o/File(2018-19)%2FBlood%20Donation%201%20november.pdf?alt=media&token=cf3534d5-97ed-44fb-a265-08e4d536567b";
                            break;
                        case 1:
                            download_url="https://firebasestorage.googleapis.com/v0/b/nssapplication-fe86c.appspot.com/o/File(2018-19)%2FBlind%20Fund%20Collection.pdf?alt=media&token=47a32910-ff8d-4868-b797-2fcfe6eb3dd6";
                            break;
                        case 2:
                            download_url="https://firebasestorage.googleapis.com/v0/b/nssapplication-fe86c.appspot.com/o/File(2018-19)%2FBlood%20Donation%2010%20January%202019.pdf?alt=media&token=d3c1b4b2-b03a-43c1-bcb8-488a13add1c0";
                            break;
                        case 3:
                            download_url="https://firebasestorage.googleapis.com/v0/b/nssapplication-fe86c.appspot.com/o/File(2018-19)%2FBlood%20Donation%2013%20_%2014%20March%202019.pdf?alt=media&token=823cd29d-7987-4353-9ff1-f0175fab5b3d";
                            break;
                        case 4:
                            download_url="https://firebasestorage.googleapis.com/v0/b/nssapplication-fe86c.appspot.com/o/File(2018-19)%2FHelp%20desk%20.pdf?alt=media&token=905373e3-a62e-48ae-a2fa-9e5dbf736be0";
                            break;
                        case 5:
                            download_url="https://firebasestorage.googleapis.com/v0/b/nssapplication-fe86c.appspot.com/o/File(2018-19)%2FIndepedent%20Day.pdf?alt=media&token=d6e84d2d-3f13-41ae-ba08-de737bb9369c";
                            break;
                        case 6:
                            download_url="https://firebasestorage.googleapis.com/v0/b/nssapplication-fe86c.appspot.com/o/File(2018-19)%2FOrientation.pdf?alt=media&token=6d418f79-de37-45fa-9854-8fb2de838709";
                            break;
                        case 7:
                            download_url="https://firebasestorage.googleapis.com/v0/b/nssapplication-fe86c.appspot.com/o/File(2018-19)%2FReport%20Of%20village%20camp%20at%20Meghva(Gana).pdf?alt=media&token=65eee2d5-0155-4aa0-88a8-c5abefa39a37";
                            break;
                        case 8:
                            download_url="https://firebasestorage.googleapis.com/v0/b/nssapplication-fe86c.appspot.com/o/File(2018-19)%2FSwachchh%20bharat%20abhiyan.pdf?alt=media&token=fc36d4a7-22f8-421d-8f2b-0ff7fcc95ea1";
                            break;
                        case 9:
                            download_url="https://firebasestorage.googleapis.com/v0/b/nssapplication-fe86c.appspot.com/o/File(2018-19)%2FTablet%20Report.pdf?alt=media&token=8b00d523-a60c-418a-bb10-3569caa6e87e";
                            break;
                        case 10:
                            download_url="https://firebasestorage.googleapis.com/v0/b/nssapplication-fe86c.appspot.com/o/File(2018-19)%2FThalassemia.pdf?alt=media&token=59ec1b1d-10c9-4db8-b8f6-5abc492df4a0";
                            break;
                        case 11:
                            download_url="https://firebasestorage.googleapis.com/v0/b/nssapplication-fe86c.appspot.com/o/File(2018-19)%2FTree%20Plantation.pdf?alt=media&token=04f0617d-1fa5-4be9-8fd8-736d68e4cab4";
                            break;
                        case 12:
                            download_url="https://firebasestorage.googleapis.com/v0/b/nssapplication-fe86c.appspot.com/o/File(2018-19)%2FReport%20of%20Annual%20Activities%20of%20Team%20NSS%20BVM.pdf?alt=media&token=67b28965-6baa-4460-b15c-1ee3910beba5";
                            break;
                    }
                }
                else {
                    download_url="https://firebasestorage.googleapis.com/v0/b/nssapplication-fe86c.appspot.com/o/File(2019-20)%2Fyoga%20day.pdf?alt=media&token=b8203c28-078c-4d44-8ba4-bf391494ed7b";
                }


                String path = Environment.getExternalStorageDirectory().toString() + "/NSS APPLICATION";

                Log.e("Files", "Path: " + path);
                File directory = new File(path);

                File[] files = directory.listFiles();
         //       Log.e("Files", "Size: " + files.length);
                String temp = filename+"_"+(year+2016)+".pdf";
                if(files!=null) {
                    for (int i = 0; i < files.length; i++) {
                        if (files[i].getName().equals(temp)) {
                            //                    Log.e("TAG", " ------>   "+files[i].getName() );
                            FileAlreadyDownloaded = true;
                            break;
                        }
                    }
                }

                if (FileAlreadyDownloaded == true) {

                    File fileToBeOpen = new File(path + "/" + temp);
                    Log.e("Tag",""+fileToBeOpen.getAbsolutePath());
                    Intent intent = new Intent();
                    intent.setAction(android.content.Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(fileToBeOpen), "application/pdf");
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(intent);


                } else {
                    // download file from server

                    if (CheckForSDCard.isSDCardPresent()) {

                        //check if app has permission to write to the external storage.
                        if (EasyPermissions.hasPermissions(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            //Get the URL entered
                            new DownloadFile().execute(download_url);



                        } else {
                            //If permission is not present request for the same.
                            EasyPermissions.requestPermissions(getActivity(), getString(R.string.write_file), WRITE_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
                        }
                    } else {
                        Toast.makeText(getActivity(), "SD Card not found", Toast.LENGTH_LONG).show();
                    }


                }


            }

        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, getActivity());
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        //Download the file once permission is granted
        new DownloadFile().execute(download_url);



    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.e("TAG", "Permission has been denied");
    }


    //-------------------------------------------------------------------------------------//


    /**
     * Async Task to download file from URL
     */
    private class DownloadFile extends AsyncTask<String, String, String> {

        private ProgressDialog progressDialog;
        private String fileName;
        private String folder;
        private boolean isDownloaded;

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = new ProgressDialog(getActivity());
            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                // getting file length
                int lengthOfFile = connection.getContentLength();


                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

          /*

           does not require mostly

                String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

            */
                String timestamp =

                        //Extract file name from URL
                        fileName = f_url[0].substring(f_url[0].lastIndexOf('/') + 1, f_url[0].length());

                //Append timestamp to file name
                fileName = filename+"_"+(year+2016)+".pdf";

                //External directory path to save file
                folder = Environment.getExternalStorageDirectory() + File.separator + "NSS APPLICATION/";

                //Create androiddeft folder if it does not exist
                File directory = new File(folder);

                if (!directory.exists()) {
                    directory.mkdirs();
                }

                // Output stream to write file
                OutputStream output = new FileOutputStream(folder + fileName);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lengthOfFile));
                    Log.e("TAG", "Progress: " + (int) ((total * 100) / lengthOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();
                return "Downloaded at: " + folder + fileName;

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return "Something went wrong";
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            progressDialog.setProgress(Integer.parseInt(progress[0]));
        }


        @Override
        protected void onPostExecute(String message) {
            // dismiss the dialog after the file was downloaded
            this.progressDialog.dismiss();

            // Display File path after downloading
            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();

            // open file after downloaded
               String path = Environment.getExternalStorageDirectory().toString() + "/NSS APPLICATION";
            File fileToBeOpen = new File(path + "/" + filename+"_"+(year+2016)+".pdf");
            Log.e("Tag",""+fileToBeOpen.getAbsolutePath());
            Intent intent = new Intent();
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(fileToBeOpen), "application/pdf");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(intent);


        }
    }


    @Override
    public boolean onBackPressed() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_Id,new ActivityReport()).commit();
        return true;
    }


}
