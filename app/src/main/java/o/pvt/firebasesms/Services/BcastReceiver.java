package o.pvt.firebasesms.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import o.pvt.firebasesms.Models.AppModel;
import o.pvt.firebasesms.Models.Message;
import o.pvt.firebasesms.R;

/**
 * Created by Saad Abdul Qadir on 4/16/2018.
 */

public class BcastReceiver extends BroadcastReceiver {

    FireBaseHelper fireBaseHelper;

    public BcastReceiver(){
        fireBaseHelper = new FireBaseHelper();
    }

    @Override
    public void onReceive(Context context, Intent rintent) {
        Intent intent = rintent;
        Bundle bundle = intent.getBundleExtra("mySMS");

        Message message = null;

        if (bundle != null) {
            Object[] smsExtra = (Object[]) bundle.get("pdus");

            for (int i = 0; i < smsExtra.length; i++) {
                SmsMessage sms = SmsMessage.createFromPdu((byte[]) smsExtra[i]);
                message = new Message();
                //take out content from sms
                String body = sms.getMessageBody().toString();
                String sender = sms.getOriginatingAddress();
                String timeStamp = new SimpleDateFormat("dd-mm-yyyy HH.mm")
                        .format(Calendar.getInstance().getTime());

                message.setBody(body);
                message.setSender(sender);

                String [] dateTime = timeStamp.split(" ");

                message.setDate(dateTime[0]);
                message.setTime(dateTime[1]);

                Log.d("FirebaseSMS",message.getBody());

                String rv_name = AppModel.getInstance().receiverName;
                if( rv_name != null) {
                    message.setReceiverName(rv_name);
                    fireBaseHelper.storeSMS(message);
                }

            }


        } else
            Log.i("mobile.cs.fsu.edu", "smsActivity : NULL SMS bundle");
    }
}
