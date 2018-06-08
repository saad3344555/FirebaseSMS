package o.pvt.firebasesms.Models;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Saad Abdul Qadir on 4/18/2018.
 */

public class AppModel {
    static AppModel appModel;
    public String receiverName;

    private AppModel(){

    }

    public static AppModel getInstance()
    {
        if(appModel == null){
            appModel = new AppModel();
        }
        return appModel;
    }

    public String getReceiverName(Context context){
        try {
            Cursor c;
            c = context.getContentResolver().query(ContactsContract.Profile.CONTENT_URI, null, null, null, null);
            c.moveToFirst();
            String name = c.getString(c.getColumnIndex("DISPLAY_NAME"));
            return name;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
}