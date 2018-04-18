package o.pvt.firebasesms.Models;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Saad Abdul Qadir on 4/18/2018.
 */

public class AppModel {
    Context context;
    static AppModel appModel;
    public String receiverName;

    private AppModel(){

    }

    public void setContext(Context context){
        this.context = context;
    }

    public static AppModel getInstance()
    {
        if(appModel == null){
            appModel = new AppModel();
            return appModel;
        }
        return null;
    }

    public void setReceiverName(){
        this.receiverName = getReceiverName();
    }

    public String getReceiverName(){
        Cursor c;
        c = context.getContentResolver().query(ContactsContract.Profile.CONTENT_URI, null, null, null, null);
        c.moveToFirst();
        String name  =  c.getString(c.getColumnIndex("display_name"));
        return name;
    }

}
