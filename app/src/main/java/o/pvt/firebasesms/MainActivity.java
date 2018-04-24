package o.pvt.firebasesms;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import o.pvt.firebasesms.Models.AppModel;
import o.pvt.firebasesms.Services.BcastReceiver;

public class MainActivity extends AppCompatActivity {
    boolean isPermissionGranted = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPermissionToReadUserContacts();
        requestSMSPermission();



    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private static final int READ_CONTACTS_PERMISSIONS_REQUEST = 1;
    private static final int RECEIVE_SMS_PERMISSIONS_REQUEST = 2;

    public void getPermissionToReadUserContacts() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            if (shouldShowRequestPermissionRationale(
                    android.Manifest.permission.READ_CONTACTS)) {
            }

            requestPermissions(new String[]{android.Manifest.permission.READ_CONTACTS},
                    READ_CONTACTS_PERMISSIONS_REQUEST);
        }else {
            isPermissionGranted = true;

        }

    }

    void requestSMSPermission(){
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    android.Manifest.permission.READ_CONTACTS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{android.Manifest.permission.READ_CONTACTS},
                        RECEIVE_SMS_PERMISSIONS_REQUEST);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
    }


    // Callback with the request from calling requestPermissions(...)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        // Make sure it's our original READ_CONTACTS request
        if (requestCode == READ_CONTACTS_PERMISSIONS_REQUEST) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                isPermissionGranted = true;
                AppModel.getInstance().setContext(this);
                AppModel.getInstance().setReceiverName();

            } else  {
                finish();
            }

        }else if(requestCode == RECEIVE_SMS_PERMISSIONS_REQUEST){
            if( grantResults.length == 1 && grantResults[0]== PackageManager.PERMISSION_GRANTED){

            }
        }
        else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
