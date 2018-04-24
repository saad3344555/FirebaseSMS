package o.pvt.firebasesms.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;



import java.text.SimpleDateFormat;
import java.util.Calendar;

import o.pvt.firebasesms.Models.AppModel;
import o.pvt.firebasesms.Models.Message;


/**
 * Created by Saad Abdul Qadir on 4/16/2018.
 */

public class BcastReceiver extends BroadcastReceiver {

    FireBaseHelper fireBaseHelper;

    @Override
    public void onReceive(Context context, Intent rintent) {
        if (rintent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {

            fireBaseHelper = new FireBaseHelper();

            Bundle bundle = rintent.getExtras();

            Message message = null;

            if (bundle != null) {
                Object[] smsExtra = (Object[]) bundle.get("pdus");
                SmsMessage[] messages = new SmsMessage[smsExtra.length];
                for (int i = 0; i < smsExtra.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) smsExtra[i]);
                }
                for (SmsMessage sms : messages) {
                    message = new Message();
                    //take out content from sms
                    String body = sms.getMessageBody().toString();
                    String sender = sms.getOriginatingAddress();
                    String timeStamp = new SimpleDateFormat("dd-mm-yyyy HH.mm")
                            .format(Calendar.getInstance().getTime());

                    message.setBody(body);
                    message.setSender(sender);
                    String[] dateTime = timeStamp.split(" ");
                    message.setDate(dateTime[0]);
                    message.setTime(dateTime[1]);

                    Log.d("FirebaseSMS", message.getBody());

                    String rv_name = AppModel.getInstance().receiverName;
                    if (rv_name != null) {
                        message.setReceiverName(rv_name);
                    }

                    try {
                        fireBaseHelper.storeSMS(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            } else
                Log.i("mobile.cs.fsu.edu", "" +
                        " : NULL SMS bundle");
        }
    }
}
