package com.team.dream.sleepsafe.hebergerinformation;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team.dream.sleepsafe.BaseApplication;
import com.team.dream.sleepsafe.hebergerinscription.IHebergerInscriptionActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HebergerInformationActivityPresenter extends AppCompatActivity implements IHebergerInformationActivityPresenter {

    private IHebergerInformationActivity view;
    private Context context;

    public HebergerInformationActivityPresenter(HebergerInformationActivity view, Context context){
        this.view = view;
        this.context = context;
    }

    @Override
    public void sendHost(final String edtSipCode, final String edtCity, final String edtRoad, final String edtPlaces, final String edtMaxDistance, final String chbTakeEm) {
        if(verifySinisterInformation(edtSipCode, edtCity, edtRoad, edtPlaces, edtMaxDistance)){

            Map<String, Object> accomodation = new HashMap<>();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
            String id = sharedPreferences.getString("id_user", "1");
            accomodation.put("address_name", edtRoad);
            accomodation.put("address_zipcode", edtSipCode);
            accomodation.put("address_city", edtCity);
            accomodation.put("nb_bed", edtPlaces);
            accomodation.put("id_user", BaseApplication.id_user);

            db.collection("accomodation")
                    .add(accomodation)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            view.launchHome();
                            Log.d("tag", "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            view.errorFields("Une erreur est survenu lors de la création de l'utlisateur (firebase)");
                        }
                    });
        }
    }

    private Boolean verifySinisterInformation(String edtSipCode, String edtCity, String edtRoad, String edtPlaces, String edtMaxDistance) {
        if (edtCity.equalsIgnoreCase("")) {
            Toast.makeText(this, "Renseignez la ville", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(edtMaxDistance.equalsIgnoreCase("")) {
            Toast.makeText(this, "Renseignez la distance maximale de prise en charge", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(edtRoad.equalsIgnoreCase("")) {
            Toast.makeText(this, "Renseignez la rue", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(edtSipCode.equalsIgnoreCase("")) {
            Toast.makeText(this, "Renseignez un code postal", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(edtPlaces.equalsIgnoreCase("")) {
            Toast.makeText(this, "Renseignez le nombre de places disponibles", Toast.LENGTH_SHORT).show();
            return false;
        }
        else  {
            return true;
        }
    }

    /**
     * Validé le sinistré :
     *  post /host , récupérer id_host
     *  post sinister/hosting (id_phone id_host)
     */
}
