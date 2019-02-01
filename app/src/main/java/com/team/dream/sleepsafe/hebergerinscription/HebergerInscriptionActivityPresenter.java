package com.team.dream.sleepsafe.hebergerinscription;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team.dream.sleepsafe.BaseApplication;
import com.team.dream.sleepsafe.herbergerconnection.HebergerConnectionActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class HebergerInscriptionActivityPresenter extends AppCompatActivity implements IHebergerInscriptionActivityPresenter {

    private IHebergerInscriptionActivity view;
    private Context context;

    public HebergerInscriptionActivityPresenter(IHebergerInscriptionActivity view, Context context){
        this.view = view;
        this.context = context;
    }

    @Override
    public void registration(final String email, final String firstname, final String lastname, final String phoneNumber, final String password, final String passwordConfirmation) {


        if(email.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || phoneNumber.isEmpty()) {
            view.errorFields("Remplis tous les champs");
            return ;
        }
        if (!TextUtils.equals(password, passwordConfirmation)) {
            view.errorFields("Les deux mots de passe ne correspondent pas");
            return ;
        }

        SharedPreferences sharedPref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        final String fcmId = sharedPref.getString(BaseApplication.FCM_ID, null);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        Task<AuthResult> task = mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "createUserWithEmail:success");

                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            Map<String, Object> user = new HashMap<>();
                            user.put("id_phone", fcmId);
                            user.put("id_user", task.getResult().getUser().getUid());
                            user.put("firstname", firstname);
                            user.put("lastname", lastname);
                            user.put("phone_number", phoneNumber);
                            db.collection("user")
                                    .add(user)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            view.launchConnection();
                                            Log.d("tag", "DocumentSnapshot added with ID: " + documentReference.getId());
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            view.errorFields("Une erreur est survenu lors de la création de l'utlisateur (firebase)");
                                        }
                                    });
                        } else {
                            view.errorFields("Une erreur est survenu lors de la création de l'utlisateur (authentification)");
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                        }
                    }
                });
    }
}
