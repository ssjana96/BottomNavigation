package com.chikpea.bottomnavigation;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ContactFragment extends Fragment {
private static final String TAG=MainActivity.class.getSimpleName();

private static final int PERMISSIONS_REQUEST_READ_CONTACTS=100;
Cursor c;
TextView textview;
ArrayList<String> contacts;
private Context context;
ListView l1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View r;
        r= inflater.inflate(R.layout.fragment_contact,null) ;
        context=r.getContext();

        l1=(ListView)r.findViewById(R.id.list);

        int permissioncheck = ContextCompat.checkSelfPermission(r.getContext(), Manifest.permission.READ_CONTACTS);
        if (permissioncheck == PackageManager.PERMISSION_GRANTED) {
            showcontacts();

        } else

        {
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_CONTACTS},PERMISSIONS_REQUEST_READ_CONTACTS);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(r.getContext(),android.R.layout.simple_list_item_1,contacts);
        l1.setAdapter(adapter);

        return r;
         }


        @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if(requestCode==PERMISSIONS_REQUEST_READ_CONTACTS)
        {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            showcontacts();
        }
       /*     else
        {
            Toast.makeText(this,"Please grant the permissions",Toast.LENGTH_SHORT).show();
        }*/
    }
}
public void showcontacts() {
    Cursor c = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
    contacts = new ArrayList<>();

    while (c.moveToNext()) {
        String contactName = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
        String phoneNumber = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        contacts.add("Name:" + contactName + "\n" + "Phone Number " + phoneNumber);
        Log.d(TAG, " Contacts" + contacts);

    }
    c.close();

}

}

