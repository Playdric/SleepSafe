package com.team.dream.sleepsafe.newsinister;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;
import com.team.dream.sleepsafe.utils.NotificationsService;
import java.util.HashMap;
import java.util.Map;
import com.google.firebase.database.DatabaseReference;
import android.widget.Toast;


public class NewSinisterActivityPresenter implements INewSinisterActivityPresenter {

    private INewSinisterActivity view;
    private Context context;

    public NewSinisterActivityPresenter( INewSinisterActivity view, Context context){
        this.context = context;
        this.view = view;
    }


    @Override
    public void sendSinister( String firstname, String lastname, int phoneNumber, int nb, String comm, String localisation, String id_phone) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> sinister = new HashMap<>();
        sinister.put("firstname", firstname);
        sinister.put("lastname", lastname);
        sinister.put("phone_number", phoneNumber);
        sinister.put("nb_people", nb);
        sinister.put("comment", comm);
        sinister.put("localisation", localisation);
        sinister.put("id_phone", id_phone);
        sinister.put("status", 0);
        Log.d("TAG", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        db.collection("sinister")
                .add(sinister)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("tag", "DocumentSnapshot added with ID: success :" + documentReference.getId());

                        FirebaseFirestore db2 = FirebaseFirestore.getInstance();
                        db2.collection("user")
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            Log.d("TAG", "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");

                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                Log.d("TAG", "ttttttttttttttttttttttttttttttttt");

                                                FirebaseMessaging.getInstance().subscribeToTopic("notifications");
                                            }
                                        } else {
                                            Log.d("TAG", "Error getting documents: ", task.getException());
                                        }
                                    }
                                });



                        view.launchHome();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("tag", "DocumentSnapshot added with ID: error :" + e.getMessage());
                        view.errorFields("Une erreur est survenu lors de la déclaration d'un sinistre (firebase)");
                    }
                });
    }
}
