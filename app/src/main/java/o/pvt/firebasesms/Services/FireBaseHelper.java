package o.pvt.firebasesms.Services;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import o.pvt.firebasesms.Models.Message;

/**
 * Created by Saad Abdul Qadir on 4/16/2018.
 */

public class FireBaseHelper {

    DatabaseReference databaseReference;

    public FireBaseHelper(){

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
