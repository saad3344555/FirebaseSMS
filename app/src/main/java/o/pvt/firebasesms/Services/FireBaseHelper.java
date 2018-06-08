package o.pvt.firebasesms.Services;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import o.pvt.firebasesms.Models.Message;

/**
 * Created by Saad Abdul Qadir on 4/16/2018.
 */

public class FireBaseHelper {

    DatabaseReference databaseReference;
    Context context;

    public FireBaseHelper(Context context){

        this.context = context;
        databaseReference =FirebaseDatabase.getInstance().getReference();

    }

    public void storeSMS(Message message){

        try{
            databaseReference.child(message.getReceiverName()).push().setValue(message);
        }catch (Exception e){
            databaseReference.child(message.getReceiverName()).push().setValue(message);
        }

    }


}
